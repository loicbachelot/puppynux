package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public abstract class Move implements Action {

    @Override
    public void use(Consciousness agent) throws ActionException {
        modifyCoordinate(agent);
    }

    @Override
    public boolean equals(Object action) {
        if (action instanceof Move){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Allows to modify the Agent's coordinate by moving
     *
     * @param agent
     * @throws ActionException
     */
    protected abstract void modifyCoordinate(Consciousness agent) throws ActionException;

    @Override
    public String toString() {
        return "Move";
    }
}
