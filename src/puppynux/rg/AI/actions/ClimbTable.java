package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class ClimbTable implements Action {

    @Override
    public void use(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (!(state == 0 || state == 4 || state == 5 || state == 8))
            throw new ActionException("Can't climb table");

        agent.setState(4);
    }

    @Override
    public String toString() {
        return "Climb Table";
    }
}
