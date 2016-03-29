package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class MoveUp extends Move {

    @Override
    protected void modifyCoordinate(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (state == 8 || (state >= 0 && state < 4))
            throw new ActionException("Can't go up");

        agent.setState(state - 4);
    }

    @Override
    public String toString() {
        return super.toString() + " Up";
    }
}
