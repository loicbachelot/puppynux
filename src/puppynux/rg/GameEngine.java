package puppynux.rg;

import config.Config;
import log.LoggerUtility;
import org.apache.log4j.Logger;
import puppynux.wr.gui.data.ConfigDialogInfo;
import puppynux.lb.env.EnvironmentManager;
import puppynux.rg.AI.AIBirth;
import puppynux.rg.AI.AIManager;
import puppynux.rg.AI.actions.EmptyActionException;
import puppynux.rg.AI.actions.OutdatedActionException;
import puppynux.rg.AI.mock.Observable;
import puppynux.rg.AI.mock.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
    private boolean attributeReward = false;
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
     * Used to create a new Environment
     */
    public void createEnvironment (String path) {
        environmentManager = new EnvironmentManager();
        environmentManager.createEnvironment(path);
    }

    //// TODO: 18/03/16 make function loadAgent()

    /**
     * Used to generate a new Agent
     */
    private void createAgent () {
        logger.info("Creating agent");
        iteration = 0;
        aiManager = new AIManager(configDialogInfo);
        aiManager.getAgent().addObserver("gameEngine", this);
        agentPlacePosition = "House";
        agentSubplacePosition = "LivingRoom";
        AIBirth.generate(aiManager.getAgent());
//        aiManager.getAgent().sense(environmentManager.getRMatrix(agentPlacePosition, agentSubplacePosition));
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

    //// TODO: 07/04/16 makes the given objects be a list of attribute for the agent created
    public void createAgent (ConfigDialogInfo info) {
        configDialogInfo = info;
        createAgent = true;
    }

    /**
     *
     * @return The Game Engine unique instance
     */
    public static GameEngine getInstance() {
        return INSTANCE;
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

    public synchronized String getAgentSubplacePosition() {
        return agentSubplacePosition;
    }

    /**
     *
     * @return An instance of the AI Manager
     */
    public AIManager getAiManager() {
        return aiManager;
    }

    public synchronized boolean isLiving () {
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
     * Used to change the number number to an array representing its coordinates
     *
     * @param state The state number
     * @return An array representing the state coordinates
     */
    public static int[] getCoordinate(int state) {
        return new int[]{(state % 4), (state / 4)};
    }

    /**
     * Used to save the Agent coordinates
     */
    public synchronized void setAgentCoordinate() {
        agentCoordinate = getCoordinate(agentState);
    }

    public synchronized void setAgentPlacePosition(String agentPlacePosition) {
        this.agentPlacePosition = agentPlacePosition;
    }

    public synchronized void setAgentSubplacePosition(String agentSubplacePosition) {
        this.agentSubplacePosition = agentSubplacePosition;
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

    @Override
    public void run() {
        logger.info("GameEngine started");
        while (isStarted) {
            if (createAgent) {
                createAgent = false;
                createEnvironment(configDialogInfo.getEnv()); //ajout ici
                createAgent();
            }

            if (attributeReward) {
                attributeReward = false;
                try {
                    aiManager.getAgent().attributeReward(reward);

                } catch (OutdatedActionException | EmptyActionException e) {
                    //// TODO: 07/04/16 remove JOtionPane from here
                    logger.warn("[GAME] " + e.getMessage());
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
        agentPlacePosition = placePosition;
        agentSubplacePosition = subplacePosition;
        agentState = state;
        setAgentCoordinate();
        aiManager.getAgent().sense(environmentManager.getRMatrix(placePosition, subplacePosition));
        notifyObserver("mainWindow", placePosition, subplacePosition, state);
    }
}
