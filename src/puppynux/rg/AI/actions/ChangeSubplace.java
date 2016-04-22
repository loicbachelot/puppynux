package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by loic on 21/04/16.
 */
public class ChangeSubplace implements Action {
    private int destination;
    public ChangeSubplace(int destination) {
        this.destination = destination;
    }

    @Override
    public void use(Consciousness agent) throws ActionException {

    }

    @Override
    public String toString() {
        return "change subplace";
    }
}
