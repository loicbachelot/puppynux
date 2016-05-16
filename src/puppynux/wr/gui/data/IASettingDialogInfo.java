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
     * @return noise
     */
    public int getNoise() {
        return noise;
    }

    /**
     *
     * @return oversight
     */
    public int getOversight() {
        return oversight;
    }

    /**
     *
     * @return velocity
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     *
     * @return refresh
     */
    public double getRefresh() {
        return refresh;
    }

    /**
     *
     * @return learnSpeed
     */
    public double getLearnSpeed() {
        return learnSpeed;
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

    /**
     * @param choice
     */
    public void setChoice(Choices choice) {
        this.choice = choice;
    }
}
