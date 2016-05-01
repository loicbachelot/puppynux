package puppynux.lb.env.objects;

/**
 * Created by loic on 06/03/16.
 * interface used to manipulate cells no matter its content
 */
public abstract class Cell {
    public boolean isPee;

    public abstract String getType();

    public void setPee(boolean pee) {
        isPee = pee;
    }

    boolean isPee () {
        return isPee;
    }
}
