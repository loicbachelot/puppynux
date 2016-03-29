package puppynux.gui.data;

/**
 * Created by william on 09/03/16.
 * Manages ConfigDialog's info
 */
public class ConfigDialogInfo implements PuppyDialogInfo {
    String name;
    int env;
    Choices choices;

    /**
     *
     * @param choices Choice transmitted by ConfigDialog
     */
    public ConfigDialogInfo(Choices choices) {
        this.choices = choices;
    }

    /**
     *
     * @param name Name transmitted by ConfigDialog
     * @param env Environment issued by ConfigDialog
     */
    public ConfigDialogInfo(String name, int env) {
        this.name = name;
        this.env = env;
    }

    /**
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Choice
     */
    @Override
    public Object getInfo() {
        return choices;
    }

    public Choices getChoice() {
        return choices;
    }
}
