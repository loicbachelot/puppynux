package puppynux.lb.env.objects;

public class Table implements Cell {
    private String type;

    public Table() {
        type = "Table";
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
