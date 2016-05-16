package puppynux.rg;

import config.Config;
import log.LoggerUtility;
import org.apache.log4j.Logger;
import puppynux.lb.env.EnvironmentManager;
import puppynux.rg.AI.AIBirth;
import puppynux.rg.AI.AIManager;
import puppynux.rg.AI.AgentLoader;
import puppynux.rg.AI.actions.Action;
import puppynux.rg.AI.actions.ActionException;
import puppynux.rg.AI.actions.EmptyActionException;
import puppynux.rg.AI.actions.OutdatedActionException;
import puppynux.rg.AI.mock.Observable;
import puppynux.rg.AI.mock.Observer;
import puppynux.wr.gui.data.ConfigDialogInfo;
import puppynux.wr.gui.data.IASettingDialogInfo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;

/**
 * Created by niamor972 on 23/02/16.
 * Parts of puppynux.rg.
 * >
 */
public class GameEngine extends Thread implements Observer, Observable {

    private final static Logger logger = LoggerUtility.getLogger(GameEngine.class);
    private final static Config config = Config.getInstance();
    private final static GameEngine INSTANCE = new GameEngine();
    private HashMap<String, Observer> observers;
    private EnvironmentManager environmentManager;
    private ConfigDialogInfo configDialogInfo;
    private AIManager aiManager;
    private boolean isStarted;
    private boolean isLiving;
    private int[] agentCoordinate = new int[2];
    private boolean createAgent = false;
    private boolean setAgent = false;
    private boolean attributeReward = false;
    private boolean forceAct = false;
    private boolean doSave = false;
    private boolean doLoad = false;
    private String pathname;
    private Action action;
    private int agentState;
    private String agentPlacePosition = "";
    private String agentSubplacePosition = "";
    private int iteration;
    private int reward;

    private GameEngine() {
        super.setName("T_GameEngine");
        logger.info("GameEngine initialization");
        isStarted = false;
        isLiving = false;
        observers = new HashMap<>();
        iteration = 0;
        reward = 0;
        agentState = -1;
    }

    //// TODO: 18/03/16 make function loadEnvironment()

    /**
     * @return The Game Engine unique instance
     */
    public static GameEngine getInstance() {
        return INSTANCE;
    }

    /**
     * Used to change the number number to an array representing its coordinates
     *
     * @param state The state number
     * @return An array representing the state coordinates
     */
    public static int[] getCoordinate(int state) {
        return new int[]{(state % 4), (state / 4)};
    }

    /**
     * Used to create a new Environment
     */
    public void createEnvironment (String path) {
        environmentManager = new EnvironmentManager();
        environmentManager.createEnvironment(path);
    }

    /**
     * Used to generate a new Agent
     */
    private void createAgent () {
        logger.info("Creating agent");
        iteration = 0;
        aiManager = new AIManager(configDialogInfo);
        aiManager.getAgent().addObserver("gameEngine", this);
        AIBirth.generate(aiManager.getAgent());
        aiManager.start();
    }

    /**
     * Used to active the reward attribution mechanism
     * @param reward The reward to grant
     */
    public synchronized void attributeReward (int reward) {
        attributeReward = true;
        this.reward = reward;
    }

    public void forceAct (Action action) {
        forceAct = true;
        this.action = action;
    }

    //// TODO: 07/04/16 makes the given objects be a list of attribute for the agent created
    public void createAgent (ConfigDialogInfo info) {
        configDialogInfo = info;
        createAgent = true;
    }

    /**
     *
     * @return The Agent's lifetime
     */
    public synchronized int getIteration() {
        return iteration;
    }

    public synchronized String getAgentPlacePosition() {
        return agentPlacePosition;
    }

    public synchronized void setAgentPlacePosition(String agentPlacePosition) {
        this.agentPlacePosition = agentPlacePosition;
    }

    public synchronized String getAgentSubplacePosition() {
        return agentSubplacePosition;
    }

    public synchronized void setAgentSubplacePosition(String agentSubplacePosition) {
        this.agentSubplacePosition = agentSubplacePosition;
    }

    /**
     *
     * @return An instance of the AI Manager
     */
    public AIManager getAiManager() {
        return aiManager;
    }

    public synchronized boolean isLiving() {
        return (aiManager != null) && aiManager.isLiving();
    }

    /**
     *
     * @return An instance of the Environment Manager
     */
    public EnvironmentManager getEnvironmentManager() {
        return environmentManager;
    }

    /**
     * Used to save the Agent coordinates
     */
    public synchronized void setAgentCoordinate() {
        agentCoordinate = getCoordinate(agentState);
    }

    public synchronized void setDebug(boolean debug) {
        aiManager.setDebug(debug);
    }

    public int[] getAgentCoordinate() {
        return agentCoordinate;
    }

    //TODO fill function, link with envManager
    //Will apply the action choosen by agent onto the environmnent
    public synchronized void applyAction(int action) {

    }

    /**
     * Used in order to end properly the game engine
     */
    public void shutDown() {
        isStarted = false;
        logger.info("[GAME] shut down");
    }

    @Override
    public synchronized void start() {
        isStarted = true;
        super.start();
    }

    public void setAgentParams (IASettingDialogInfo info) {
        configDialogInfo.setInfo(info);
        setAgent = true;
    }

    public void setAgentParams () {
        aiManager.setVelocity(configDialogInfo.getVelocity());
        aiManager.getAgent().set(configDialogInfo);
    }

    public void save () {
        doSave = true;
    }

    private void save (String filename) throws IOException {
        logger.info("[GAME] saving");
        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(
                                new File(getClass().getClassLoader().getResource("resources/backup/" + filename + ".dat").toString()))));
        oos.writeObject(new AgentLoader(
                configDialogInfo, aiManager.getAgent().getMemory(), aiManager.getAgent().getActionMap()));
        oos.close();

        aiManager.printQ();
    }

    public void load (String filename) {
        doLoad = true;
        pathname = filename;
    }
    private void load () throws IOException, ClassNotFoundException {
        logger.info("[GAME] loading");
        ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(
                                new File(getClass().getClassLoader().getResource("resources/backup/" + pathname + ".dat").toString()))));
        AgentLoader loader = (AgentLoader) ois.readObject();
        configDialogInfo = loader.getInfo();
        createEnvironment(configDialogInfo.getEnv());
        createAgent();
        aiManager.getAgent().setMemory(loader.getMemory());
    }

    @Override
    public void run() {
        logger.info("GameEngine started");
        while (isStarted) {
            if (createAgent) {
                createAgent = false;
                createEnvironment(configDialogInfo.getEnv()); //ajout ici
                createAgent();
            }

            if (setAgent) {
                setAgent = false;
                setAgentParams();
            }

            if (doLoad) {
                doLoad = false;
                try {
                    load();
                } catch (Exception e) {
                    //// TODO: 5/15/16 remove JOPTION from here
                    logger.warn("[GAME] " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog((Component) observers.get("mainWindow"), e.getMessage());
                }
            }

            if (doSave) {
                doSave = false;
                try {
                    save(configDialogInfo.getName());
                } catch (IOException e) {
                    //// TODO: 5/15/16 remove JOPTION from here
                    logger.warn("[GAME] " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog((Component) observers.get("mainWindow"), e.getMessage());
                }
            }

            if (forceAct) {
                forceAct = false;
                aiManager.pause();
                try {
                    aiManager.getAgent().forceAct(action);
                } catch (ActionException e) {
                    //// TODO: 5/15/16 remove JOPTION from here
                    logger.warn("[GAME] " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog((Component) observers.get("mainWindow"), e.getMessage());
                }
            }

            if (attributeReward) {
                attributeReward = false;
                try {
                    aiManager.getAgent().attributeReward(reward);

                } catch (OutdatedActionException | EmptyActionException e) {
                    //// TODO: 07/04/16 remove JOtionPane from here
                    logger.warn("[GAME] " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog((Component) observers.get("mainWindow"), e.getMessage());
                }
            }
            try {
                Thread.sleep(0L, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public synchronized void update(String placePosition, String subplacePosition, int state) {
        logger.trace("[GAME] agent moved to " + state);
        agentState = state;
        setAgentCoordinate();
        if (!subplacePosition.equals(this.agentSubplacePosition)) {
            agentPlacePosition = placePosition;
            agentSubplacePosition = subplacePosition;
            aiManager.getAgent().sense(environmentManager.getRMatrix(placePosition, subplacePosition));
        }
        notifyObserver("mainWindow", placePosition, subplacePosition, state);
    }
}
