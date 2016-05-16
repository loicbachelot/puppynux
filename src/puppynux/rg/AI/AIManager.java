package puppynux.rg.AI;

import config.Config;
import org.apache.log4j.Logger;
import puppynux.rg.AI.actions.Action;
import puppynux.rg.AI.actions.Move;
import puppynux.rg.GameEngine;
import puppynux.wr.gui.data.ConfigDialogInfo;
import puppynux.rg.AI.actions.ActionException;

import java.io.*;
import java.util.Map;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * > get information from env, check agent's state in it and give him the row in R
 * >
 */

//TODO fixer un seuil de connaissance totale et faire varier celui de l'agent
//de manière à ce que l'agent sache ce qu'il ne connait pas
public class AIManager extends Thread implements Serializable {
    //TODO :
    //stock a pile of action / state
    //ask for reward to user
    //when answers, attribute reward to last action on pile
    //depile while time
    private final static Logger logger = Logger.getLogger(AIManager.class);
    private final static Config config = Config.getInstance();
    private Consciousness agent;
    private boolean isStarted, isPaused;
    private int velocity;
    private long period;
    private boolean isDebug;

    public AIManager(ConfigDialogInfo info) {
        super();
        super.setName("T_AIManager");
        isStarted = false;
        isDebug = false;
        isPaused = true;
        velocity = info.getVelocity();
        periodByRuleOf3();
        agent = new Agent(info);
    }

    public AIManager() {
        super();
        super.setName("T_AIManager");
        isStarted = false;
        isPaused = true;
        velocity = 0;
        period = 2000;
        agent = new Agent();
    }

    /**
     * Used for exporting the Agent's Q-Matrix for interpretation
     *
     * @throws IOException
     */
    public void printQ() throws IOException {
        File file = new File("src/log/q_logs/Q" + agent.getName() + ".txt");
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        printWriter.println(agent.getActionMap());
        for (Map.Entry<String, QMatrix> entry : agent.getMemory().entrySet()) {
            printWriter.println(entry.getKey());
            printWriter.println(entry.getValue().toString());
        }
        printWriter.close();
        logger.info("QMatrix printed");
    }
    /**
     * Used for exporting the Agent's Q-Matrix for interpretation
     *
     * @throws IOException
     */
    public void oldPrintQ() throws IOException {
/*
        long saved = (long) config.get("SAVED_FILES") + 1;
        config.put("SAVED_FILES", saved);
        File file = new File("src/log/q_logs/Q" + saved + ".txt");
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        printWriter.println(agent.getQ().toString());
        printWriter.close();
        logger.info("QMatrix printed");
*/
    }

    private void periodByRuleOf3() {
        period = (long) (2000 - (velocity * 150));
    }

    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
        periodByRuleOf3();
    }

    public long getVelocity() {
        return velocity;
    }

    /**
     * @return The Agent instance
     */
    public Consciousness getAgent() {
        return agent;
    }

    /**
     * Used to end properly the agent
     */
    public void kill() {
        isStarted = false;
        logger.info("[AIMANAGER] agent killed");
    }

    /**
     * Used for pausing the Agent's decision making
     */
    public synchronized void pause() {
        isPaused = true;
    }

    /**
     * Used to resume the agent's decision making
     */
    public synchronized void play() {
        isPaused = false;
    }

    public synchronized boolean isActing() {
        return !isPaused;
    }

    public synchronized boolean isLiving() {
        return isStarted;
    }

    @Override
    public synchronized void start() {
        isStarted = true;
        super.start();
        logger.info("[AIMANAGER] agent started");
    }

    /**
     * Generate the Agent, then does it act as a time factor
     */
    @Override
    public void run() {
        while (isStarted) {
            if (isPaused) {
                try {
                    Thread.sleep(0L, 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (isDebug) {
                boolean isMoving = false;
                if (agent.getAction() instanceof Move) {
                    isMoving = true;
                }
                if (!isMoving) {

                } else {
                    try {
                        agent.routine();
                        Thread.sleep(period);
                    } catch (ActionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    agent.routine();
                    Thread.sleep(period);
                } catch (ActionException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
