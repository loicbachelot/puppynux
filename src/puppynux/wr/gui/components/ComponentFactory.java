package puppynux.wr.gui.components;

import log.LoggerUtility;
import org.apache.log4j.Logger;
import puppynux.wr.gui.*;
import puppynux.wr.gui.objects.Dog;

import javax.swing.*;

/**
 * Created by niamor972 on 09/03/16.
 * Parts of puppynux.wr.gui.
 * Contains all loggers
 */
public abstract class ComponentFactory {

    private final static Logger logger = LoggerUtility.getLogger(ComponentFactory.class);

    /**
     * Initializes FirstDialog
     * @param parent Parent frame
     * @return FirstDialog
     */
    public static FirstDialog initFirstDialog(JFrame parent) {
        FirstDialog firstDialog = new FirstDialog(parent, "Welcome", true);
        logger.info("FirstDialog creation");
        return firstDialog;
    }

    /**
     * Initializes ConfigDialog
     * @param parent Parent frame
     * @param title ConfigDialog's title
     * @return ConfigDialog
     */
    public static ConfigDialog initConfigDialog(JFrame parent, String title) {
        ConfigDialog configDialog = new ConfigDialog(parent, title, true);
        logger.info("ConfigDialog creation");
        return configDialog;
    }

    /**
     * Initializes LoadDialog
     * @param parent Parent frame
     * @return LoadDialog
     */
    public static LoadDialog initLoadDialog(JFrame parent) {
        LoadDialog loadDialog = new LoadDialog(parent, "Loader", true);
        logger.info("LoadDialog creation");
        return loadDialog;
    }

    /**
     * Initializes IASettingDialog
     * @param parent Parent frame
     * @return IASettingDialog
     */
    public static IASettingDialog initIASettingDialog(JFrame parent) {
        IASettingDialog iaSettingDialog = new IASettingDialog(parent, "IA parameters", true);
        logger.info("IASettingDialog");
        return iaSettingDialog;
    }

    /**
     * Initializes Dashboard
     * @return Dashboard
     */
    public static Dashboard initDashboard() {
        Dashboard dashboard = new Dashboard(new Dog(1,1), 50);
        logger.info("Dashboard initialization");
        return dashboard;
    }

    /**
     * Initializes BackgroundPanel
     * @return BackgroundPanel
     */
    public static BackgroundPanel initBackgroundPanel() {
        BackgroundPanel backgroundPanel = new BackgroundPanel(0);
        logger.info("Environment Panel initialization");
        return backgroundPanel;
    }

    /**
     * Initializes MenuBar
     * @param mainWindow MainWindow where MenuBar is
     * @return MenuBar
     */
    public static MenuBar initMenuBar(MainWindow mainWindow) {
        MenuBar menuBar = new MenuBar(mainWindow);
        logger.info("Menu Bar initialization");
        return menuBar;
    }

    /**
     * Initializes RewardsPanel
     * @return RewardsPanel
     */
    public static RewardsPanel initRewardsPanel() {
        RewardsPanel rewardsPanel = new RewardsPanel();
        logger.info("South Panel initialization");
        return rewardsPanel;
    }

    /**
     * Initializes NewsPanel
     * @return NewsPanel
     */
    public static NewsPanel initNewsPanel(String name) {
        NewsPanel newsPanel = new NewsPanel(name);
        logger.info("Side Panel initialization");
        return newsPanel;
    }

    /**
     * Initializes AnimationPanel
     * @return AnimationPanel
     */
    public static AnimationPanel initAnimationPanel() {
        AnimationPanel animationPanel = new AnimationPanel();
        logger.info("Animation Panel initialization");
        return animationPanel;
    }
}
