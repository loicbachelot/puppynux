package puppynux.wr.gui.data;

/**
 * Created by william on 10/03/16.
 * Manages IASettingDialog's info
 */
public class IASettingDialogInfo implements PuppyDialogInfo {
    private Choices choice;
    private double learnSpeed, refresh;
    private int velocity, oversight, noise;


    public IASettingDialogInfo(double learnSpeed, double refresh, int velocity, int oversight, int noise) {
        this.learnSpeed = learnSpeed;
        this.refresh = refresh;
        this.velocity = velocity;
        this.oversight = oversight;
        this.noise = noise;
        choice = Choices.CANCEL;
    }

    /**
     *
     * @return
     */
    public int getNoise() {
        return noise;
    }

    /**
     *
     * @return
     */
    public int getOversight() {
        return oversight;
    }

    /**
     *
     * @return
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     *
     * @return
     */
    public double getRefresh() {
        return refresh;
    }

    /**
     *
     * @return
     */
    public double getLearnSpeed() {
        return learnSpeed;
    }

    /**
     *
     * @param choice
     */
    public void setChoice(Choices choice) {
        this.choice = choice;
    }

    /**
     * @return Choice
     */
    @Override
    public Object getInfo() {
        return choice;
    }

    @Override
    public Choices getChoice() {
        return choice;
    }
}
