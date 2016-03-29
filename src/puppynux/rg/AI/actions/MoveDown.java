package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class MoveDown extends Move {

    @Override
    protected void modifyCoordinate(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (state == 0 || state == 11 || (state >= 12 && state < 16))
            throw new ActionException("Can't go down");

        agent.setState(state + 4);
    }

    @Override
    public String toString() {
        return super.toString() + " Down";
    }
}
