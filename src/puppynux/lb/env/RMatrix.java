package puppynux.lb.env;

import puppynux.rg.AI.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by loic
 * >
 */
public class RMatrix {

    private final static int STATE_NUMBER = 16;
    private final static int ACTION_NUMBER = 8; //16;

    private ArrayList<HashMap<Action, Boolean>> actionList = new ArrayList<>();
    private ArrayList<Action> possibleActions = new ArrayList<>();

    public RMatrix() {
        for (int i = 0; i < STATE_NUMBER; i++) {
            actionList.add(new HashMap<Action, Boolean>());
        }
    }

    public void addPossibleAction(Action action){
        possibleActions.add(action);
    }

    public ArrayList<HashMap<Action, Boolean>> getActionList() {
        return actionList;
    }

    public ArrayList<Action> getPossibleActions() {
        return possibleActions;
    }

    public HashMap<Action, Boolean> getStateReward(int state) {
        return actionList.get(state);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        int j;
        for (HashMap<Action, Boolean> hashmap : actionList) {
            buffer.append("line" + i + ": ");
            i++;
            j=0;
            for (Map.Entry entry: hashmap.entrySet()) {
                buffer.append(j + "[" + entry.getKey().toString() + "]");
                j++;
            }
            buffer.append("\n");
        }
        for (Action action: possibleActions) {
            buffer.append(action.toString() + "\n");
        }
        return buffer.toString();
    }
}