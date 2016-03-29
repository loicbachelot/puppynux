package puppynux.rg.AI;

import puppynux.rg.AI.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niamor972 on 24/03/16.
 * Parts of puppynux.rg.AI.
 * >
 */
public class QMatrix {

    private ArrayList<HashMap<Action, Double>> qmatrix;

    public QMatrix (int knownStates) {
        qmatrix = new ArrayList<>();
        for (int i = 0; i < knownStates; i++) {
            qmatrix.add(new HashMap<Action, Double>());
        }
    }

    public HashMap<Action, Double> getStateActions(int state) {
        return qmatrix.get(state);
    }

    public double getActionReward(int state, Action action) {
        if  (qmatrix.get(state).containsKey(action)) {
            return qmatrix.get(state).get(action);
        }
        qmatrix.get(state).put(action, 0.);
        return 0;
    }

    public void setReward(int state, Action action, double reward) {
        qmatrix.get(state).put(action, reward);
    }

    @Override
    public String toString() {
        String out = "# X (state) Y (action) Z (reward)\n";
        int i = 0;
        for (HashMap<Action, Double> stateData : qmatrix) {
            for (Map.Entry<Action, Double> entry : stateData.entrySet()) {
                out += String.valueOf(i) + " " + entry.getKey() + " " + entry.getValue() + "\n";
            }
            i++;
        }
        return out;
    }
}
