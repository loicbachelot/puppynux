package puppynux.rg.AI.mock;


/**
 * Created by niamor972 on 15/03/16.
 * Parts of puppynux.rg.AI.mock.
 * >
 */
public interface Observable {

    /**
     * Used for adding an Observer to the Observer list
     *
     * @param name The name of the Observer to add
     * @param obs The instance of the Observer
     */
    void addObserver(String name, Observer obs);

    /**
     * Used for removing an Observer from the Observer list
     * @param name The name of the Observer to remove
     */
    void removeObserver(String name);

    /**
     * Clear the Observer list
     */
    void removeObservers();

    /**
     * Used for notifying to an Observer a state change
     *
     * @see Observer
     * @param name The name of the Observer
     * @param state The new state
     */
    void notifyObserver(String name, int state);

    /**
     * Used for notifying to an Observer a place or subplace position change
     *
     * @see Observer
     * @param name The name of the Observer
     * @param placePosition The new place position
     * @param subplacePosition The new subplace position
     */
    void notifyObserver(String name, String placePosition, String subplacePosition);
}
