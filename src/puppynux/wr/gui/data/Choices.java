package puppynux.wr.gui.data;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.wr.gui.data.
 * Enumeration for many buttons
 */
public enum Choices {
    NEW("NEW"),
    LOAD("LOAD"),
    CANCEL("CANCEL"),
    OPEN("OPEN"),
    OK("OK"),
    EDIT("EDIT"),
    STATS("STATS");

    private String name;

    Choices(String str) {
        name = str;
    }

    /**
     * @return Choice's name
     */
    @Override
    public String toString() {
        return name;
    }
}
