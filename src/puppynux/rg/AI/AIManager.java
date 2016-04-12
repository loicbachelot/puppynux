package puppynux.rg.AI;

import config.Config;
import org.apache.log4j.Logger;
import puppynux.rg.AI.actions.ActionException;

import java.io.*;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * > get information from env, check agent's state in it and give him the row in R
 * >
 */

//TODO fixer un seuil de connaissance totale et faire varier celui de l'agent
    //de manière à ce que l'agent sache ce qu'il ne connait pas
public class AIManager extends Thread {
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

    public AIManager() {
        super();
        super.setName("T_AIManager");
        isStarted = true;
        isPaused = false;
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
        long saved = (long)config.get("SAVED_FILES") + 1;
        config.put("SAVED_FILES", saved);
        File file = new File("src/log/q_logs/Q" + saved + ".txt");
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        printWriter.println(agent.getQ().toString());
        printWriter.close();
        logger.info("QMatrix printed");
    }

    //// TODO: 17/03/16 find equation for velocity (0-10) = (500-2000) ms for wait()
    public void setVelocity(int velocity) {
        this.velocity = velocity;

    }

    public long getVelocity() {
        return velocity;
    }

    /**
     *
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
    public synchronized void pause () {
        isPaused = true;
    }

    /**
     * Used to resume the agent's decision making
     */
    public synchronized void play () {
        isPaused = false;
    }


    @Override
    public synchronized void start() {
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
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
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
