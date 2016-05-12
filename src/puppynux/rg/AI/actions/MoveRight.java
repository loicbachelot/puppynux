package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class MoveRight extends Move {

    @Override
    protected void modifyCoordinate(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (state % 4 == 3)
            throw new ActionException("Can't go right");

        agent.setState(state + 1);
    }

    @Override
    public String toString() {
        return super.toString() + " Right";
    }
}
