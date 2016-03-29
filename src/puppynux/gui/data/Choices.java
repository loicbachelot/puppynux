package puppynux.gui.data;

import puppynux.gui.ConfigDialog;
import puppynux.gui.PuppyDialog;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.gui.data.
 * Enumeration for many buttons
 */
public enum Choices {
    NEW("NEW"),
    LOAD("LOAD"),
    CANCEL("CANCEL"),
    OPEN("OPEN"),
    OK("OK"),
    EDIT("EDIT");

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
