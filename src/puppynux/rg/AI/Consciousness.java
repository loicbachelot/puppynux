package puppynux.rg.AI;

import config.Config;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import puppynux.lb.env.RMatrix;
import puppynux.rg.AI.actions.*;
import puppynux.rg.AI.mock.EnvironmentManager;
import puppynux.rg.AI.mock.Observable;
import puppynux.rg.AI.mock.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * >
 */
public abstract class Consciousness implements Observable {

    private final static Logger logger = Logger.getLogger(Consciousness.class);
    protected final double LEARN_FACTOR = 0.8;
    protected final double  ACTUALISATION_FACTOR = 1;
    protected final int NOISE_FACTOR = 1000;
    protected HashMap<String, Observer> observers;
    protected Action [] actionTab;
    protected QMatrix Q;
    protected int knownActions;
    protected int actualState;
    protected int oldState;
    protected Action action;
    protected String name;
    protected ArrayList<HashMap<Action, Boolean>> envTab;
    protected LinkedList<ActionData> actionStack;
    protected String placePosition;
    protected String subplacePosition;

    public Consciousness() {
        logger.info("Consciousness awake");
        knownActions = 0;
        actionStack = new LinkedList<>();
        observers = new HashMap<>();
        Q = new QMatrix(16);
        name = "test";
    }



    /**
     * Used to initialize the action tab from config
     */
    public void initActionTab(JSONObject jsonObject) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        knownActions = jsonObject.size();
        actionTab = new Action[knownActions];
        for (int i = 0; i < knownActions; i++) {
            Class cls = Class.forName("puppynux.rg.AI.actions." + jsonObject.get(String.valueOf(i)).toString());
            actionTab[i] = (Action) cls.newInstance();
        }
        logger.info("[AGENT] Action tab initialized with " + jsonObject.toString());
    }

    /**
     * Used for changing the state of the Agent
     *
     * @see Observable#notifyObserver(String, int)
     * @param state The new state for the Agent
     */
    public synchronized void setState (int state) {
        this.actualState = state;
        logger.trace("[AGENT] state changed to : " + state);
        notifyObserver("gameEngine", state);
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
     * The method used when a reward is received in order to learn it
     * @param reward The reward to attribute to the last action
     */
    public void attributeReward (int reward) {
        actionStack.getFirst().setReward(reward);
        logger.trace("[AGENT] learned : " + actionStack.getFirst().toString());
        learn(unstackAction());
    }

    public void forceAct (Action action) throws ActionException {
        this.action = action;
        act();
//        attributeReward(envTab.get(oldState)[lastAction]);
    }

    /**
     * A routine used by the Agent to act
     *
     * @throws ActionException If using the chosen action causes inconsistencies
     */
    public void routine() throws ActionException {
        action = chooseAction(envTab.get(actualState));
        act();
        int reward = 0;
        if (action instanceof Pee)
            reward = -100;
        if (action instanceof PlayBall)
            reward = 100;
        if (action instanceof ClimbTable)
            reward = -50;
        attributeReward(reward);
    }

    /**
     * The method used by the Agent to act
     * @throws ActionException If using the action causes inconsistencies
     */
    protected void act() throws ActionException {
        oldState = actualState;
        action.use(this);
        stackAction(new ActionData(oldState, action, actualState));
        logger.trace("[AGENT] from " + oldState + " performs " + action + " (to " + actualState + ") with expected reward : " + Q.getActionReward(oldState, action)+ "\n");
    }

    /**
     * Used by the Agent for decision making
     *
     * @param possibilities The tab of possibilities for actual state
     * @return The chosen action
     */
    protected Action chooseAction (HashMap<Action, Boolean> possibilities) {
        double max = 0.0;
        Action action = new Pee();
        for (Map.Entry<Action, Boolean> entry : possibilities.entrySet()) {
            if (!entry.getValue())
                continue;
            double reward = Q.getActionReward(actualState, entry.getKey());
            double noise = Math.random() * NOISE_FACTOR;
            if (reward + noise > max) {
                action = entry.getKey();
                max = reward + noise;
            }
        }
        return action;
    }

    //// TODO: 26/03/16 make learn() makes the difference between learning and reinforcement
    /**
     * Used by the Agent for reinforcement
     *
     * @param actionData The Action Data to learn
     */
    protected void learn(ActionData actionData) {
        int state = actionData.getState(), nextState = actionData.getNextState(), reward = actionData.getReward();
        Action action = actionData.getAction();
        double max = futureSight(nextState);

        double r = reward + (int) (LEARN_FACTOR * max);
        Q.setReward(state, action, r);
    }

    /**
     * Used by the Agent to predict the maximum reward available in next state
     *
     * @param nextState The Agent's next state to check reward
     * @return The maximum reward available from next state
     */
    protected double futureSight (int nextState) {
        double max = 0;
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
    protected void stackAction (ActionData actionData) {
        actionStack.addFirst(actionData);
    }

    /**
     *
     * @return The last Action Data recorded
     */
    protected ActionData unstackAction () {
        return actionStack.removeFirst();
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

    /**
     *
     * @return The instance of the Agent's Q-Matrix
     */
    public QMatrix getQ() {
        return Q;
    }

    public String getSubplacePosition() {
        return subplacePosition;
    }

    public void setSubplacePosition(String subplacePosition) {
        this.subplacePosition = subplacePosition;
    }

    public String getPlacePosition() {
        return placePosition;
    }

    public void setPlacePosition(String placePosition) {
        this.placePosition = placePosition;
    }

    /**
     *
     * @return The name of the Agent
     */
    public String getName() {
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
    public void notifyObserver(String name, int state) {
        observers.get(name).update(state);
    }
}
