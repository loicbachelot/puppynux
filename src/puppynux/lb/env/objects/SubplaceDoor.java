package puppynux.lb.env.objects;

/**
 * Created by loic on 22/04/16.
 */
public abstract class SubplaceDoor extends Cell {
    private String type;
    private String subplace;
    int x, y;

    public SubplaceDoor(String subplace, String type, int x, int y) {
        this.subplace = subplace;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSubplace() {
        return subplace;
    }

    public void setSubplace(String subplace) {
        this.subplace = subplace;
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
