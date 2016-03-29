package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class Stay extends Move {
    @Override
    protected void modifyCoordinate(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (state == 4 || state == 15)
            throw new ActionException("Can't stay here");
        agent.notifyObserver("gameEngine", state);
    }

    @Override
    public String toString() {
        return "Stay";
    }
}
