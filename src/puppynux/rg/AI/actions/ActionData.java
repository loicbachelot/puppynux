package puppynux.rg.AI.actions;

/**
 * Created by niamor972 on 23/03/16.
 * Parts of puppynux.rg.AI.actions.
 * >
 * @see Action
 * This is en storage class for recording data when an action is performed, in order to assign a reward when delivered
 */
public class ActionData {

    private int state, nextState, reward, age;
    private String subplace;
    private Action action;

    public ActionData (int state, Action action, int nextState, int age, String subplace) {
        this.age = age;
        this.state = state;
        this.action = action;
        this.nextState = nextState;
        this.subplace = subplace;
        this.reward = 0;
    }

    /**
     *
     * @return The age of the agent when the action were performed
     */
    public int getAge () {
        return age;
    }

    /**
     *
     * @return The state of the agent when the action were performed
     */
    public int getState() {
        return state;
    }

    /**
     *
     * @return The state reached by the agent after performing the action
     */
    public int getNextState() {
        return nextState;
    }

    /**
     *
     * @return The reward assigned to the action, 0 if no reward had been given
     */
    public int getReward() {
        return reward;
    }

    /**
     * Used to associate a reward to the action
     * @param reward The reward to grant
     */
    public void setReward(int reward) {
        this.reward = reward;
    }

    /**
     *
     * @return An instance of the action that were performed
     */
    public Action getAction() {
        return action;
    }

    /**
     *
     * @return
     */
    public String getSubplace() {
        return subplace;
    }

    @Override
    public String toString() {
        return "Action Data : [State : " + state + ", Action : " + action + ", NextState : " + nextState + ", Reward : " + reward + " / Age : "+ age + "]";
    }
}
