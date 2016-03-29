package puppynux.rg.AI.mock;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.mock.
 * >
 */
public class RMatrix {

    private final static int STATE_NUMBER = 16;
    private final static int ACTION_NUMBER = 8; //16;

    private int[][] tab2D = new int[STATE_NUMBER][ACTION_NUMBER];

    public RMatrix () {
        for (int i = 0; i < STATE_NUMBER; i++) {
            for (int j = 0; j < ACTION_NUMBER; j++) {
                tab2D[i][j] = -1;
            }
        }
    }

    public int[][] getTab2D () {
        return tab2D;
    }

    public int[] getStateReward (int state) {
        return tab2D[state];
    }

    public int getActionReward (int state, int action) {
        return getStateReward(state)[action];
    }

    public void setReward(int state, int action, int reward) {
        tab2D[state][action] = reward;
    }

}