package puppynux.lb.env.objects;

/**
 * Created by loic on 22/04/16.
 */
public interface SubplaceDoor extends Cell {
    String getType();
    int getDestination();
    void setDestination(int destination);
}