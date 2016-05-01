package puppynux.wr.gui.data;

/**
 * Created by william on 10/03/16.
 * Manages LoadDialog's info
 */
public class LoadDialogInfo implements PuppyDialogInfo {

    private Choices choice;

    /**
     *
     * @param choice Choice transmitted by LoadDialog
     */
    public LoadDialogInfo (Choices choice) {
        this.choice = choice;
    }

    /**
     *
     * @return Choice
     */
    @Override
    public Object getInfo() {
        return choice;
    }

    public Choices getChoice() {
        return choice;
    }
}
