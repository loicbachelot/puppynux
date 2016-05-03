package puppynux.wr.gui;

import org.apache.log4j.Logger;
import puppynux.wr.gui.components.*;
import puppynux.wr.gui.data.*;
import puppynux.rg.AI.Agent;
import puppynux.rg.AI.mock.Observer;
import puppynux.rg.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by niamor972 on 09/03/16.
 * Parts of puppynux.wr.gui.
 * >
 */
public class MainWindow extends JFrame implements Observer {

    public final static Font titlePanelFont = new Font("Monospaced", Font.BOLD, 30);
    public final static Font font = new Font("Monospaced", Font.CENTER_BASELINE, 15);
    public final static Font mainTitleFont = new Font("Monospaced", Font.BOLD, 60);
    public final static Color backgroundsColor = new Color(174, 215, 247);
    public final static Color bordersColor = new Color(50, 50, 50);

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Dimension screenSize = tk.getScreenSize();
    public static final int screenHeight = screenSize.height;
    public static final int screenWidth = screenSize.width;

    private final static Logger logger = Logger.getLogger(MainWindow.class);
    private final static GameEngine gameEngine = GameEngine.getInstance();
    private Dashboard dashboard;
    private BackgroundPanel environmentPanel;
    private RewardsPanel rewardsPanel;
    private FirstDialogInfo firstDialogInfo;
    private ConfigDialogInfo configDialogInfo;
    private LoadDialogInfo loadDialogInfo;
    private IASettingDialogInfo iaSettingDialogInfo;
    private MenuBar menuBar;
    private BorderLayout borderLayout;
    private AnimationPanel animation;
    private NewsPanel newsPanel;
    private boolean isRunning;
    private int state;

    public MainWindow() throws InterruptedException {
        GameEngine.getInstance().addObserver("mainWindow", this);
        dashboard = ComponentFactory.initDashboard();
        rewardsPanel = ComponentFactory.initRewardsPanel();
        environmentPanel = ComponentFactory.initBackgroundPanel();
        menuBar = ComponentFactory.initMenuBar(this);
        animation = ComponentFactory.initAnimationPanel();
        borderLayout = new BorderLayout();
        state = 0;
        this.setTitle("Puppynux");
        this.setSize(windowSize());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(menuBar);
        this.add(animation);
        this.setContentPane(getContentPane());
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isRunning = false;
                System.out.println("test closing");
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("test closed");
            }
        });


        go();
    }

    /*
        @Override
        public void update(int[] coor) {
            dashboard.getObject().setX(coor[0]);
            dashboard.getObject().setY(coor[1]);
            dashboard.drawObjects();
        }
    */
    public void go() throws InterruptedException {
        isRunning = true;

        showFirstDialog();

        switch (firstDialogInfo.getChoice()) {
            case NEW:
                state = 1;
                break;
            case CANCEL:
                JOptionPane.showMessageDialog(null, "No session launched", "WARN", JOptionPane.INFORMATION_MESSAGE);
                break;
            case LOAD:
                state = 2;
                break;
        }

        while (isRunning) {
            switch (state) {
                case 1:
                    state = 0;
                    showConfigDialog();
                    if (configDialogInfo.getChoice().equals(Choices.OK)) {
                        state = 3;
                    }
                    break;
                case 2:
                    state = 0;
                    showLoadDialog();
                    break;
                case 3:
                    state = 4;
                    newsPanel = ComponentFactory.initNewsPanel(configDialogInfo.getName());
                    gameEngine.createAgent(configDialogInfo);
                    break;
                case 4:
                    if (gameEngine.isLiving()) {
                        state = 0;
                        animation.setVisible(false);
                        add(dashboard, BorderLayout.CENTER);
                        add(rewardsPanel, BorderLayout.SOUTH);
                        add(newsPanel, BorderLayout.EAST);
                    }
                    else {
                        Thread.sleep(1L);
                    }
                    break;
                default:
                    Thread.sleep(0L, 1);
                    break;
            }
        }
    }

    public void shutDown() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void startGame(ConfigDialogInfo info) {
        configDialogInfo = info;
        state = 3;
    }

    public static Dimension windowSize() {
//        Config config = null;
//        config = config.getInstance();
//        try {
//            config.load("src/config/config.json");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        Dimension dim = new Dimension();
//        int width = (int) config.getLong("WINDOW_WIDTH");
//        int height = (int) config.getLong("WINDOW_HEIGHT");

        int width = rec.width;
        int height = rec.height;
        dim.width = width;
        dim.height = height;

        return dim;
    }

    public void showFirstDialog() {
        FirstDialog firstDialog = ComponentFactory.initFirstDialog(this);
        firstDialogInfo = firstDialog.showDialog();
    }

    public void showLoadDialog() {
        LoadDialog loadDialog = ComponentFactory.initLoadDialog(this);
        loadDialogInfo = loadDialog.showDialog();
    }

    public void showConfigDialog() {
        ConfigDialog configDialog = ComponentFactory.initConfigDialog(this, "Initializer");
        configDialogInfo = configDialog.showDialog();
    }

    public void showIASettingDialog() {
        IASettingDialog iaSettingDialog = ComponentFactory.initIASettingDialog(this);
        iaSettingDialogInfo = iaSettingDialog.showDialog();
    }

    @Override
    public void update(String placePosition, String subplacePosition, int state) {
        int oldX = gameEngine.getAiManager().getAgent().getOldState() % 4;
        int oldY = gameEngine.getAiManager().getAgent().getOldState() / 4;
        int x = state % 4;
        int y = state / 4;

        if (oldX > x) {
            dashboard.getAnimal().setImage("src/resources/img/dogBack.png");
        } else {
            dashboard.getAnimal().setImage("src/resources/img/dog.png");
        }
        dashboard.setPlacePosition(placePosition);
        dashboard.setSubplacePosition(subplacePosition);
        dashboard.getAnimal().setX(x);
        dashboard.getAnimal().setY(y);


        Agent agent = (Agent) gameEngine.getAiManager().getAgent();
        newsPanel.getIterationLabel().setText("Iteration : " + gameEngine.getIteration());
        newsPanel.getLocationLabel().setText("Agent's location : " + state);
        newsPanel.getActionLabel().setText("Agent performed action \"" + agent.getAction() + "\""); //TODO getAction
        newsPanel.getRewardLabel().setText("With expected reward : " + agent.getQ().getActionReward(agent.getOldState(), agent.getAction())); //TODO getReward
        repaint();
        logger.trace("[WINDOW] updated");
    }


    public BackgroundPanel getEnvironmentPanel() {
        return environmentPanel;
    }

    public AnimationPanel getAnimation() {
        return animation;
    }

    public static void main(String[] args) throws InterruptedException {
        MainWindow mainWindow = new MainWindow();
    }
}
