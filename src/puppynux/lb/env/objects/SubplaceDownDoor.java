package puppynux.lb.env.objects;

/**
 * Created by loic on 22/04/16.
 */
public class SubplaceDownDoor implements SubplaceDoor {
    private String type;
    private int destination;

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public SubplaceDownDoor(int destination) {
        this.destination = destination;
        type = "SubplaceDownDoor";
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
