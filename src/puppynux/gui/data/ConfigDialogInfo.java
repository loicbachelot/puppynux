package puppynux.gui.data;

/**
 * Created by william on 09/03/16.
 * Manages ConfigDialog's info
 */
public class ConfigDialogInfo implements PuppyDialogInfo {
    String name;
    int env, velocity, oversight, noise;;
    double learnSpeed,refreshFrequency;
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
    public ConfigDialogInfo(String name, int env, double learnSpeed, double refreshFrequency, int velocity, int oversight, int noise) {
        this.name = name;
        this.env = env;
        this.learnSpeed = learnSpeed;
        this.refreshFrequency = refreshFrequency;
        this.velocity = velocity;
        this.oversight = oversight;
        this.noise = noise;
    }

    /**
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    public int getEnv() {
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
        return choices;
    }

    public Choices getChoice() {
        return choices;
    }
}
