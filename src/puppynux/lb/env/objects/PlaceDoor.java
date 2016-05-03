package puppynux.lb.env.objects;

/**
 * Created by loic on 21/04/16.
 */
public abstract class PlaceDoor extends Cell {
    private String type;
    private String place;
    private String subplace;
    int x, y;

    public PlaceDoor(String place, String subplace, String type, int x, int y) {
        this.place = place;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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