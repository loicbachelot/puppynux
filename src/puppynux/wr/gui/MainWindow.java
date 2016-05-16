package puppynux.wr.gui;

import chart.BarChart_AWT;
import chart.Charts_AWT;
import chart.PieChart_AWT;
import org.apache.log4j.Logger;
import org.jfree.ui.RefineryUtilities;
import puppynux.rg.AI.AgentLoader;
import puppynux.wr.gui.components.*;
import puppynux.wr.gui.data.*;
import puppynux.rg.AI.Agent;
import puppynux.rg.AI.mock.Observer;
import puppynux.rg.GameEngine;
import puppynux.wr.gui.components.*;
import puppynux.wr.gui.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * Created by niamor972 on 09/03/16.
 * Parts of puppynux.wr.gui.
 * Main window of the project
 */
public class MainWindow extends JFrame implements Observer {

    public final static Font titlePanelFont = new Font("Monospaced", Font.BOLD, 30);
    public final static Font font = new Font("Monospaced", Font.CENTER_BASELINE, 15);
    public final static Font mainTitleFont = new Font("Monospaced", Font.BOLD, 60);
    public final static Color backgroundsColor = new Color(174, 215, 247);
    public final static Color bordersColor = new Color(50, 50, 50);

    private final static Logger logger = Logger.getLogger(MainWindow.class);
    private final static GameEngine gameEngine = GameEngine.getInstance();
    private Dashboard dashboard;
    private BackgroundPanel backgroundPanel;
    private RewardsPanel rewardsPanel;
    private FirstDialogInfo firstDialogInfo;
    private ConfigDialogInfo configDialogInfo;
    private LoadDialogInfo loadDialogInfo;
    private IASettingDialogInfo iaSettingDialogInfo;
    private AgentLoader loader;
    private MenuBar menuBar;
    private BorderLayout borderLayout;
    private AnimationPanel animation;
    private NewsPanel newsPanel;
    private boolean isRunning;
    private int state;
    private String agentSubPlacePosition = "";

    public MainWindow() throws InterruptedException {
        GameEngine.getInstance().addObserver("mainWindow", this);
        state = 0;
        factoryInit();
        initMainWindow();
        windowListener();
        go();
    }

    /**
     * Allow to dynamically set window size
     *
     * @return dim
     */
    public static Dimension windowSize() {
        Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        Dimension dim = new Dimension();
        int width = rec.width;
        int height = rec.height;
        dim.width = width;
        dim.height = height;
        return dim;
    }

    public static void main(String[] args) throws InterruptedException {
        MainWindow mainWindow = new MainWindow();
    }

    /**
     * Initializes elements of main window
     */
    public void factoryInit() {
        dashboard = ComponentFactory.initDashboard();
        rewardsPanel = ComponentFactory.initRewardsPanel();
        backgroundPanel = ComponentFactory.initBackgroundPanel();
        menuBar = ComponentFactory.initMenuBar(this);
        animation = ComponentFactory.initAnimationPanel();
        borderLayout = new BorderLayout();
    }

    /**
     * Initializes main window parameters
     */
    public void initMainWindow() {
        this.setTitle("Puppynux");
        this.setSize(windowSize());
        this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("resources/img/dog.png")).getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(menuBar);
        this.add(animation);
        this.setContentPane(getContentPane());
        this.setVisible(true);
    }

    public void windowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isRunning = false;
                System.out.println("Program closing");
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Program closed");
            }
        });
    }

    /**
     * Rhythm the gameplay
     *
     * @throws InterruptedException
     */
    public void go() throws InterruptedException {
        isRunning = true;
        showFirstDialog();
        switch (firstDialogInfo.getChoice()) {
            case NEW:
                state = 1;
                break;
            case LOAD:
                state = 2;
                break;
            case STATS:
                state = 4;
                break;
            case CANCEL:
            default:
                JOptionPane.showMessageDialog(null, "No session launched", "WARN", JOptionPane.INFORMATION_MESSAGE);
                break;
        }


        while (isRunning) {
            switch (state) {
                case 1:
                    logger.info("[WINDOW] New agent");
                    state = 0;
                    showConfigDialog();
                    if (configDialogInfo.getChoice().equals(Choices.OK)) {
                        state = 3;
                        newsPanel = ComponentFactory.initNewsPanel(configDialogInfo.getName());
                        gameEngine.createAgent(configDialogInfo);
                    } else
                        JOptionPane.showMessageDialog(null, "No session launched", "WARN", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                    state = 0;
                    logger.info("[WINDOW] Load agent");
                    showLoadDialog();
                    if (loadDialogInfo.getChoice().equals(Choices.OK)) {
                        state = 3;
                        newsPanel = ComponentFactory.initNewsPanel(loadDialogInfo.getPath());
                        gameEngine.load(loadDialogInfo.getPath());
                    } else
                        JOptionPane.showMessageDialog(null, "No session launched", "WARN", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    if (gameEngine.isLiving()) {
                        logger.info("[WINDOW] Agent created");
                        state = 0;
                        animation.setVisible(false);
                        add(dashboard, BorderLayout.CENTER);
                        add(rewardsPanel, BorderLayout.SOUTH);
                        add(newsPanel, BorderLayout.EAST);
                    } else {
                        Thread.sleep(1L);
                    }
                    break;
                case 4:
                    state = 0;
                    logger.info("[WINDOW] Stats");
                    showLoadDialog();
                    if (loadDialogInfo.getChoice().equals(Choices.OK)) {
                        state = 7;
                        try {
                            ObjectInputStream ois = new ObjectInputStream(
                                    new BufferedInputStream(
                                            new FileInputStream(
                                                    new File("src/resources/backup/" + loadDialogInfo.getPath() + ".dat"))));
                            loader = (AgentLoader)ois.readObject();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 5:
                    state = 0;
                    logger.info("[WINDOW] Agent settings");
                    gameEngine.getAiManager().pause();
                    showIASettingDialog();
                    if (iaSettingDialogInfo.getChoice().equals(Choices.OK)) {
                        gameEngine.setAgentParams(iaSettingDialogInfo);
                    }
                    break;
                case 6:
                    state = 0;
                    logger.info("[WINDOW] Save agent");
                    gameEngine.save();
                    break;
                case 7:
                    //// TODO: 5/16/16 if living presents total + bar for actual subplace if not presents only total for selected
                    state = 0;
                    logger.info("[WINDOW] Agent stats");
                    Charts_AWT chart = new Charts_AWT(loader.getInfo().getName(),
                            loader.getMemory(), loader.getActionMap());
                    break;
                default:
                    Thread.sleep(0L, 1);
                    break;
            }
        }
    }

    /**
     * @param state
     */
    @Override
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Makes firstDialog initialize
     */
    public void showFirstDialog() {
        FirstDialog firstDialog = ComponentFactory.initFirstDialog(this);
        firstDialogInfo = firstDialog.showDialog();
    }

    /**
     * Makes loadDialog initialize
     */
    public void showLoadDialog() {
        LoadDialog loadDialog = ComponentFactory.initLoadDialog(this);
        loadDialogInfo = loadDialog.showDialog();
    }

    /**
     * Makes configDialog initialize
     */
    public void showConfigDialog() {
        ConfigDialog configDialog = ComponentFactory.initConfigDialog(this, "Initializer");
        configDialogInfo = configDialog.showDialog();
    }

    /**
     * Makes iaSettingDialog initialize
     */
    public void showIASettingDialog() {
        IASettingDialog iaSettingDialog = ComponentFactory.initIASettingDialog(this);
        iaSettingDialogInfo = iaSettingDialog.showDialog();
    }

    /**
     * Set the better image for walking dog
     */
    public void dogOrientation(int state) {
        int oldX = gameEngine.getAiManager().getAgent().getOldState() % 4;
        int x = state % 4;
        int y = state / 4;

        if (oldX > x) {
            dashboard.getAnimal().setImage("resources/img/dogBack.png");
        } else {
            dashboard.getAnimal().setImage("resources/img/dog.png");
        }
        dashboard.getAnimal().setX(x);
        dashboard.getAnimal().setY(y);
    }

    /**
     * Set the place and the subplace in dashboard
     *
     * @param placePosition
     * @param subplacePosition
     */
    public void placePosition(String placePosition, String subplacePosition) {
        dashboard.setPlacePosition(placePosition);
        dashboard.setSubplacePosition(subplacePosition);
    }

    /**
     * Set JComboBox action
     *
     * @param placePosition
     * @param subplacePosition
     */
    public void possibleAction(String placePosition, String subplacePosition) {
        rewardsPanel.setJComboBox(gameEngine.getEnvironmentManager().
                getRMatrix(placePosition, subplacePosition).getPossibleActions());
        rewardsPanel.setJComboBox(GameEngine.getInstance().getEnvironmentManager().
                getRMatrix(placePosition, subplacePosition).getPossibleActions());
    }

    /**
     * Set what is agent doing, his reward, ...
     */
    public void agentNow(int state) {
        Agent agent = (Agent) gameEngine.getAiManager().getAgent();
        newsPanel.getIterationLabel().setText("Iteration : " + gameEngine.getIteration());
        newsPanel.getPlaceLabel().setText("Agent is in the " + agent.getPlacePosition() + ", more precisely in the " + agent.getSubplacePosition());
        newsPanel.getLocationLabel().setText("Agent's location : " + state);
        if (agent.getAction() == null) {
            newsPanel.getActionLabel().setText("Agent performed action \"none\"");
            newsPanel.getRewardLabel().setText("With expected reward : none");
        } else {
            newsPanel.getActionLabel().setText("Agent performed action \"" + agent.getAction() + "\"");
            newsPanel.getRewardLabel().setText("With expected reward : " + agent.getQ().getActionReward(agent.getOldState(), agent.getAction()));
        }
    }

    /**
     * Update the window
     *
     * @param placePosition
     * @param subplacePosition
     * @param state
     */
    @Override
    public void update(String placePosition, String subplacePosition, int state) {
        dogOrientation(state);
        placePosition(placePosition, subplacePosition);
        possibleAction(placePosition, subplacePosition);
        agentNow(state);
        repaint();
        logger.trace("[WINDOW] updated");
    }

    /**
     * @return backgroundPanel
     */
    public BackgroundPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    /**
     * @return animationPanel
     */
    public AnimationPanel getAnimation() {
        return animation;
    }
}
