package puppynux.wr.gui.data;

/**
 * Created by william on 10/03/16.
 * Manages LoadDialog's info
 */
public class LoadDialogInfo implements PuppyDialogInfo {

    private Choices choice;
    private String path;

    public LoadDialogInfo (String path) {
        this.path = path;
    }

    /**
     *
     * @return Choice
     */
    @Override
    public Object getInfo() {
        return choice;
    }

    public void setChoice(Choices choice) {
        this.choice = choice;
    }

    public String getPath() {
        return path;
    }

    public Choices getChoice() {
        return choice;
    }
}
