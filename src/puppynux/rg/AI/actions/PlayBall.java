package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class PlayBall implements Action {
    public int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void use(Consciousness agent) throws ActionException {
        agent.setState(position);
    }

    @Override
    public String toString() {
        return "Play Ball";
    }
}
