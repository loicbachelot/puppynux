package puppynux.wr.gui.data;

/**
 * Created by william on 09/03/16.
 * Manages ConfigDialog's info
 */
public class ConfigDialogInfo implements PuppyDialogInfo {
    private String name, env;
    private int velocity, oversight, noise;
    private double learnSpeed,refreshFrequency;
    private Choices choice;

    /**
     *
     * @param choice Choice transmitted by ConfigDialog
     */
    public ConfigDialogInfo(Choices choice) {
        this.choice = choice;
    }

    /**
     *
     * @param name Name transmitted by ConfigDialog
     * @param env Environment issued by ConfigDialog
     */
    public ConfigDialogInfo(String name, String env, double learnSpeed, double refreshFrequency, int velocity, int oversight, int noise) {
        this.name = name;
        this.env = env;
        this.learnSpeed = learnSpeed;
        this.refreshFrequency = refreshFrequency;
        this.velocity = velocity;
        this.oversight = oversight;
        this.noise = noise;
        choice = Choices.CANCEL;
    }

    /**
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    public String getEnv() {
        return env;
    }

    public double getLearnSpeed() {
        return learnSpeed;
    }

    public double getRefreshFrequency() {
        return refreshFrequency;
    }

    public int getNoise() {
        return noise;
    }

    public int getOversight() {
        return oversight;
    }

    public int getVelocity() {
        return velocity;
    }

    /**
     * @return Choice
     */
    @Override
    public Object getInfo() {
        return choice;
    }

    public Choices getChoice() {
        return choice;
    }

    public void setChoice(Choices choice) {
        this.choice = choice;
    }
}
