package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public class ClimbTable implements Action {
    public int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object action) {
        if(action instanceof ClimbTable) {
            return ((ClimbTable) action).getPosition() == position;
        }
        else{
            return false;
        }
    }

    @Override
    public void use(Consciousness agent) throws ActionException {
        agent.setState(position);
    }

    @Override
    public String toString() {
        return "climb table";
    }
}
