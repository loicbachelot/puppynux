package puppynux.lb.env.objects;

/**
 * Created by loic on 21/04/16.
 */
public class SubplaceTopDoor extends Cell implements SubplaceDoor {
    private String type;
    private String subplace1;
    private String subplace2;

    public String getSubplace1() {
        return subplace1;
    }

    public void setSubplace1(String subplace1) {
        this.subplace1 = subplace1;
    }

    public String getSubplace2() {
        return subplace2;
    }

    public void setSubplace2(String subplace2) {
        this.subplace2 = subplace2;
    }

    public SubplaceTopDoor(String subplace1, String subplace2) {
        this.subplace1 = subplace1;
        this.subplace2 = subplace2;
        type = "SubplaceTopDoor";
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
