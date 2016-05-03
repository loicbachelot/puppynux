package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class Pee implements Action {

    @Override
    public void use(Consciousness agent) throws ActionException {
//        agent.notifyObserver("gameEngine", agent.getActualState());
    }

    @Override
    public String toString() {
        return "Pee";
    }
}
