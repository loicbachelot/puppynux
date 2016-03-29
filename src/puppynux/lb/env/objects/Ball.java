package puppynux.lb.env.objects;

/**
 * Created by loic
 * Parts of puppynux.lb.env.objects
 * >creat a object ball in the virtual environment
 */

public class Ball implements Cell {
    private String type;

    public Ball() {
        type = "Ball";
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
