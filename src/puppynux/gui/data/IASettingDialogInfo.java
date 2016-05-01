package puppynux.gui.data;

/**
 * Created by william on 10/03/16.
 * Manages IASettingDialog's info
 */
public class IASettingDialogInfo implements PuppyDialogInfo {
    private Double value;

    /**
     *
     * @param value Value transmitted by IASettingDialog
     */
    public IASettingDialogInfo(double value) {
        this.value = value;
    }

    /**
     * @return Choice
     */
    @Override
    public Object getInfo() {
        return null;
    }

    @Override
    public Choices getChoice() {
        return null;
    }
}
