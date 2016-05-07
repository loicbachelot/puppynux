package puppynux.wr.gui.data;

/**
 * Created by william on 09/03/16.
 * Manages ConfigDialog's info
 */
public class ConfigDialogInfo implements PuppyDialogInfo {
    private String name, env, config;
    private int velocity, oversight, noise;
    private double learnSpeed, refreshFrequency;
    private Choices choice;

    /**
     * @param choice Choice transmitted by ConfigDialog
     */
    public ConfigDialogInfo(Choices choice) {
        this.choice = choice;
    }

    /**
     * @param name Name transmitted by ConfigDialog
     * @param env  Environment issued by ConfigDialog
     */
    public ConfigDialogInfo(String name, String env, String config) {
        this.name = name;
        this.env = env;
        this.config = config;
        choice = Choices.CANCEL;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return env
     */
    public String getEnv() {
        return env;
    }

    /**
     * @return config
     */
    public String getConfig() {
        return config;
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
