package puppynux.lb.env.objects;

/**
 * Created by loic on 06/03/16.
 */
public class Empty extends Cell{
    private String type;
    public Empty(){
        type = "Empty";
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
