package puppynux.rg.AI.mock;

/**
 * Created by niamor972 on 15/03/16.
 * Parts of puppynux.rg.AI.mock.
 * >
 */
public interface Observer {
    /**
     * @see Observable
     * @param state
     */
    void update(int state);
    void update();
}
