package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

import java.io.Serializable;

/**
 * Created by niamor972 on 12/03/16.
 * Parts of puppynux.AI.actions.
 * >
 */
public interface Action extends Serializable{

    /**
     * Used by Agent in order to routine no matter the action
     *
     * @param agent The agent to use action on
     * @throws ActionException If an error occured while performing action
     */
    void use(Consciousness agent) throws ActionException;
    int hashCode();
    boolean equals(Object action);
    String toString();
}
