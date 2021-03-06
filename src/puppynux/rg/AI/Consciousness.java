package puppynux.rg.AI;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import puppynux.lb.env.RMatrix;
import puppynux.rg.AI.actions.*;
import puppynux.rg.AI.mock.Observable;
import puppynux.rg.AI.mock.Observer;
import puppynux.wr.gui.data.ConfigDialogInfo;

import java.io.Serializable;
import java.util.*;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * >
 */
public abstract class Consciousness implements Serializable, Observable {

    private final static Logger logger = Logger.getLogger(Consciousness.class);
    private double LEARN_FACTOR;
    private double  ACTUALISATION_FACTOR;
    private int NOISE_FACTOR;
    private int OVERSIGHT_FACTOR;
    private HashMap<String, QMatrix> memory;
    private HashMap<String, Observer> observers;
    private HashMap<String, Integer> actionMap;
    private QMatrix Q;
    private int knownActions;
    private volatile int actualState;
    private int oldState;
    protected volatile Action action;
    protected volatile String name;
    private ArrayList<HashMap<Action, Boolean>> envTab;
    private LinkedList<ActionData> actionStack;
    private String placePosition;
    private String subplacePosition;
    private String oldSubplacePosition;
    private int age;

    public Consciousness(ConfigDialogInfo info) {
        logger.info("Consciousness awaking");
        knownActions = 0;
        age = 0;
        memory = new HashMap<>();
        actionStack = new LinkedList<>();
        actionMap = new HashMap<>();
        observers = new HashMap<>();
        name = info.getName();
        LEARN_FACTOR = info.getLearnSpeed();
        ACTUALISATION_FACTOR = info.getRefreshFrequency();
        NOISE_FACTOR = info.getNoise();
        OVERSIGHT_FACTOR = info.getOversight();

    }

    public void initActionMap (ArrayList<Action> actionList) {
        knownActions = actionList.size();
        for (Action action: actionList) {
            actionMap.put(action.toString(), 0);
        }
        logger.info("[AGENT] Action tab initialized with " + actionList.toString());
    }

    /**
     * Used to initialize the action tab from config
     */
    public void initActionMap(JSONObject jsonObject) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        knownActions = jsonObject.size();
        for (int i = 0; i < knownActions; i++) {
            Class cls = Class.forName("puppynux.rg.AI.actions." + jsonObject.get(String.valueOf(i)).toString());
            Action action = (Action) cls.newInstance();
            actionMap.put(action.toString(), 0);
        }
        logger.info("[AGENT] Action tab initialized with " + jsonObject.toString());
    }

    public void setMemory(HashMap<String, QMatrix> memory) {
        this.memory = memory;
        Q = memory.get(subplacePosition);
    }

    /**
     * Used for changing the state of the Agent
     *
     * @see Observable#notifyObserver(String, String, String, int)
     * @param state The new state for the Agent
     */
    public synchronized void setState (int state) {
        this.actualState = state;
        logger.trace("[AGENT] state changed to : " + state);
        notifyObserver("gameEngine", placePosition, subplacePosition, state);
    }

    /**
     * Used to update the Agent possibilities in function of its position
     *
     * @param rMatrix The representation of the environment
     */
    public void sense(RMatrix rMatrix) {
        envTab = rMatrix.getActionList();
        logger.trace("[AGENT] discerned");
    }

    /**
     * The method used by user to order the agent
     * @param action The action ordered to the agent
     * @throws ActionException If an error occurs while performing action
     */
    public void forceAct (Action action) throws ActionException {
        this.action = action;
        act();
        learn();
    }

    /**
     * A routine used by the Agent to act
     *
     * @throws ActionException If using the chosen action causes inconsistencies
     */
    public void routine() throws ActionException {
        action = chooseAction(envTab.get(actualState));
        act();
        learn();
    }

    /**
     * The method used by the Agent to act
     * @throws ActionException If using the action causes inconsistencies
     */
    private void act() throws ActionException {
        oldState = actualState;
        oldSubplacePosition = subplacePosition;
        action.use(this);
        int count = actionMap.containsKey(action.toString()) ? actionMap.get(action.toString()) : 0;
        actionMap.put(action.toString(), count + 1);
        if (!(action instanceof Move)) {
            stackAction(new ActionData(oldState, action, actualState, age, oldSubplacePosition));
        }
        age++;
        logger.trace("[AGENT] from " + oldState + " performs " + action + " (to " + actualState + ") with expected reward : " + Q.getActionReward(oldState, action)+ "\n");
    }

    /**
     * Used by the Agent for decision making
     *
     * @param possibilities The tab of possibilities for actual state
     * @return The chosen action
     */
    private Action chooseAction (HashMap<Action, Boolean> possibilities) {
        double max = 0.0;
        Action action = null;
        for (Map.Entry<Action, Boolean> entry : possibilities.entrySet()) {
            if (!actionMap.containsKey(entry.getKey().toString())) {
                actionMap.put(entry.getKey().toString(), 0);
            }
            if (!entry.getValue())
                continue;
            double reward = Q.getActionReward(actualState, entry.getKey());
            double noise = Math.random() * NOISE_FACTOR;
            if (entry.getKey() instanceof Pee || entry.getKey() instanceof Stay)
                noise *= 0.75;
            if (reward + noise >= max) {
                action = entry.getKey();
                max = reward + noise;
            }
        }
        return action;
    }

    /**
     * The method used when a reward is received in order to learn it
     *
     * @param reward The reward to attribute to the last action
     * @throws EmptyStackException If a reward is given while no action had been recorded
     * @throws OutdatedActionException If a reward is given when the last action was performed too long
     */
    public void attributeReward (int reward) throws EmptyActionException, OutdatedActionException {
        if (actionStack.isEmpty()) {
            throw new EmptyActionException(name + " has no action recorded");
        }
        if (age - actionStack.getFirst().getAge() >= OVERSIGHT_FACTOR) {
            actionStack = new LinkedList<>();
            throw new OutdatedActionException(name + " has forgot his last action.");
        }
        actionStack.getFirst().setReward(reward);
        logger.trace("[AGENT] learned : " + actionStack.getFirst().toString());
        learn(unstackAction());
    }

    /**
     * Used by the agent to propagate rewards in QMatrix
     */
    private void learn () {
        double max = futureSight(actualState);
        double r = LEARN_FACTOR * ACTUALISATION_FACTOR * max + Q.getActionReward(oldState, action) * (1 - LEARN_FACTOR);
        memory.get(oldSubplacePosition).setReward(oldState, action, r);
//        Q.setReward(oldState, action, r);
    }

    /**
     * Used by the Agent for reinforcement
     * @param actionData The Action Data to learn
     */
    private void learn(ActionData actionData) {
        logger.trace("learned " + actionData.getReward());
        memory.get(actionData.getSubplace()).
                addReward(actionData.getState(), actionData.getAction(), LEARN_FACTOR * actionData.getReward());
//        Q.addReward(actionData.getState(), actionData.getAction(), LEARN_FACTOR * actionData.getReward());
    }

    /**
     * Used by the Agent to predict the maximum reward available in next state
     *
     * @param nextState The Agent's next state to check reward
     * @return The maximum reward available from next state
     */
    private double futureSight (int nextState) {
        if (Q.getStateActions(nextState).isEmpty()) {
            return 0;
        }
        double max = -100;
        for (double reward :
                Q.getStateActions(nextState).values()) {
            if (reward > max)
                max = reward;
        }

        return max;
    }

    /**
     *
     * @param actionData The Action Data to stack
     */
    private void stackAction (ActionData actionData) {
        actionStack.addFirst(actionData);
    }

    /**
     *
     * @return The last Action Data recorded
     */
    private ActionData unstackAction() {
        return actionStack.removeFirst();
    }

    public void set (ConfigDialogInfo config) {
        LEARN_FACTOR = config.getLearnSpeed();
        ACTUALISATION_FACTOR = config.getRefreshFrequency();
        NOISE_FACTOR = config.getNoise();
        OVERSIGHT_FACTOR = config.getOversight();
        actionStack = new LinkedList<>();
    }

    /**
     *
     * @return The action map where key represents the action name and value its occurence
     */
    public HashMap<String, Integer> getActionMap() {
        return actionMap;
    }

    /**
     *
     * @return The Agent's actual state
     */
    public int getActualState() {
        return actualState;
    }

    /**
     *
     * @return The Agent's old state
     */
    public int getOldState () {
        return oldState;
    }

    public Action getAction() {
        return action;
    }

    /**
     *
     * @return The instance of the Agent's Q-Matrix
     */
    public QMatrix getQ() {
        return Q;
    }

    public QMatrix getQfor (String subplacePosition) {
        return memory.get(subplacePosition);
    }

    public HashMap<String, QMatrix> getMemory() {
        return memory;
    }

    /**
     *
     * @return A string representing the subplace position of the agent
     */
    public String getSubplacePosition() {
        return subplacePosition;
    }

    /**
     *
     * @param subplacePosition A string representing the subplace position of the agent
     */
    public void setSubplacePosition(String subplacePosition, int state) {
        this.subplacePosition = subplacePosition;
        if (!memory.containsKey(subplacePosition)) {
            memory.put(subplacePosition, new QMatrix(16));
        }
        Q = memory.get(subplacePosition);
        setState(state);
    }

    /**
     *
     * @return A string representing the place position of the agent
     */
    public String getPlacePosition() {
        return placePosition;
    }

    /**
     *
     * @param placePosition The new place for the agent
     * @param subplacePosition The new subplace for the agent
     * @param state The new state for the agent
     */
    public void setPlacePosition(String placePosition, String subplacePosition, int state) {
        this.placePosition = placePosition;
        setSubplacePosition(subplacePosition, state);
    }

    /**
     *
     * @return The name of the Agent
     */
    public synchronized String getName() {
        return name;
    }

    @Override
    public void addObserver(String name, Observer obs) {
        observers.put(name, obs);
    }

    @Override
    public void removeObserver(String name) {
        observers.remove(name);
    }

    @Override
    public void removeObservers() {
        observers = new HashMap<>();
    }

    @Override
    public void notifyObserver(String name, String placePosition, String subplacePosition, int state) {
        observers.get(name).update(placePosition, subplacePosition, state);
    }
}
