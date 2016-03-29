package puppynux.rg.AI.actions;

/**
 * Created by niamor972 on 23/03/16.
 * Parts of puppynux.rg.AI.actions.
 * >
 */
public class ActionData {

    private int state, nextState, reward;
    private Action action;

    public ActionData (int state, Action action, int nextState) {
        this.state = state;
        this.action = action;
        this.nextState = nextState;
        this.reward = 0;
    }

    public int getState() {
        return state;
    }

    public int getNextState() {
        return nextState;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Action Data : [State : " + state + ", Action : " + action + ", NextState : " + nextState + ", Reward : " + reward + "]";
    }
}
