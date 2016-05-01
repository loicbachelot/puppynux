package puppynux.lb.env.objects;

/**
 * Created by loic on 21/04/16.
 */
public class PlaceDoor  extends Cell {
    private String type;
    private int destination;

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public PlaceDoor(int destination) {
        this.destination = destination;
        type = "PlaceDoor";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
