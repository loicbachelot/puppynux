package puppynux.rg.AI;

import config.Config;
import org.apache.log4j.Logger;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * >
 */
public class OldQMatrix {

    private final static Logger logger = Logger.getLogger(OldQMatrix.class);
    private final static Config config = Config.getInstance();
    private final static int KNOWN_STATES = (int)(long)config.get("KNOWN_STATES");
    private final static int KNOWN_ACTIONS = (int)(long)config.get("KNOWN_ACTIONS");

    private double[][] tab2D = new double[KNOWN_STATES][KNOWN_ACTIONS];

    public OldQMatrix() {
        for (int i = 0; i < KNOWN_STATES; i++) {
            for (int j = 0; j < KNOWN_ACTIONS; j++) {
                tab2D[i][j] = 0;
            }
        }
        logger.info("QMatrix initialization");
    }

    /**
     *
     * @return The matrix used for memory storage
     */
    public double[][] getTab2D () {
        return tab2D;
    }

    /**
     * Used to know the tab of rewards associated to a state
     * @param state The state, representing a row number
     * @return The array of matrix associated to the state
     */
    public double[] getStateReward (int state) {
        return tab2D[state];
    }

    /**
     * Used to know the reward associated to a state / action
     *
     * @param state The state where the action were performed
     * @param action The action that were used
     * @return The reward associated to the action if performed in the state
     */
    public double getActionReward (int state, int action) {
        return getStateReward(state)[action];
    }

    /**
     * Used to restore learned value to percentage relative to the highest reward
     *
     * @param q An instance of a Q-Matrix
     * @return A new instance of a normalized Q-Matrix
     */
    public static OldQMatrix normalizedQ (OldQMatrix q) {
        OldQMatrix out = new OldQMatrix();
        double max = 0;
        for (int i = 0; i < KNOWN_STATES; i++) {
            for (int j = 0; j < KNOWN_ACTIONS; j++) {
                if (q.getActionReward(i, j) > max) {
                    max = q.getActionReward(i, j);
                }
            }
        }

        for (int i = 0; i < KNOWN_STATES; i++) {
            for (int j = 0; j < KNOWN_ACTIONS; j++) {
                double r = (q.getActionReward(i, j) * 100 / max);
                out.setReward(i, j, r);
            }
        }

        return out;
    }

    /**
     *
     * @param state The state where the action were performed
     * @param action The action that were used
     * @param reward The reward associated to the action when performed in the state
     */
    public void setReward(int state, int action, double reward) {
        tab2D[state][action] = reward;
    }

    public void addReward(int state, int action, double reward) {
        tab2D[state][action] += reward;
    }

    public String toString () {
        String out = "";
        for (int i = 0; i < KNOWN_STATES; i++) {
            for (int j = 0; j < KNOWN_ACTIONS; j++) {
                out += String.valueOf((int)tab2D[i][j]);
                out += "\t";
            }
            out += "\n";
        }
        return out;
    }
}