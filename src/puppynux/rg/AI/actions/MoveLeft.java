package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class MoveLeft extends Move {

    @Override
    protected void modifyCoordinate(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (state % 4 == 0)
            throw new ActionException("Can't go left");

        agent.setState(state - 1);
    }

    @Override
    public String toString() {
        return super.toString() + " Left";
    }
}
