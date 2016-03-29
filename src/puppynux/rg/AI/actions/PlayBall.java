package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class PlayBall implements Action {

    @Override
    public void use(Consciousness agent) throws ActionException {
        int state = agent.getActualState();
        if (!(state == 11 || state == 14 || state == 15))
            throw new ActionException("Can't play ball");

        agent.setState(15);
    }

    @Override
    public String toString() {
        return "Play Ball";
    }
}
