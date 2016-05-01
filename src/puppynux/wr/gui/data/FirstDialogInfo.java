package puppynux.wr.gui.data;

/**
 * Created by william on 10/03/16.
 * Manages FirstDialog's info
 */
public class FirstDialogInfo implements PuppyDialogInfo {

    private Choices choice;

    /**
     *
     * @param choice Choice transmitted by FirstDialog
     */
    public FirstDialogInfo (Choices choice) {
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

    public Choices getChoice () {
        return choice;
    }
}
