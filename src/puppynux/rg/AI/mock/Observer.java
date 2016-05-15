package puppynux.rg.AI.mock;

import java.io.Serializable;

/**
 * Created by niamor972 on 15/03/16.
 * Parts of puppynux.rg.AI.mock.
 * >
 */
public interface Observer extends Serializable {
    /**
     *
     * @see Observable
     * @param placePosition
     * @param subplacePosition
     * @param state
     */
    void update(String placePosition, String subplacePosition, int state);
}
