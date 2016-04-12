package puppynux.gui;

import config.Config;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import puppynux.gui.components.*;
import puppynux.gui.data.*;
import puppynux.rg.GameEngine;
import puppynux.rg.AI.mock.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by niamor972 on 09/03/16.
 * Parts of puppynux.gui.
 * >
 */
public class MainWindow extends JFrame implements Observer {

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
        newsPanel = ComponentFactory.initNewsPanel();
        environmentPanel = ComponentFactory.initBackgroundPanel();
        menuBar = ComponentFactory.initMenuBar(this);
        animation = ComponentFactory.initAnimationPanel();
        borderLayout = new BorderLayout();
        state = 0;
        this.setTitle("Puppynux");
        this.setSize(windowSize());
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
                    break;
                case 2:
                    state = 0;
                    showLoadDialog();
                    break;
                case 3:
                    state = 0;
                    animation.setVisible(false);
                    add(dashboard, BorderLayout.CENTER);
                    add(rewardsPanel, BorderLayout.SOUTH);
                    add(newsPanel, BorderLayout.EAST);
                    GameEngine.getInstance().createAgent(configDialogInfo);
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

    public void startGame(Object obj) {
        state = 3;
    }

    public static Dimension windowSize() {
        Config config = null;
        config = config.getInstance();
        try {
            config.load("src/config/config.json");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dimension dim = new Dimension();
        int width = (int) config.getLong("WINDOW_WIDTH");
        int height = (int) config.getLong("WINDOW_HEIGHT");
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
    public void update(int state) {
        dashboard.getAnimal().setX(state % 4);
        dashboard.getAnimal().setY(state / 4);
        newsPanel.getDebugLabel().setText("Agent reached " + state + " Iteration = " + gameEngine.getIteration());
        repaint();
        logger.trace("[WINDOW] updated");
    }

    @Override
    public void update() {

    }


    public static void main(String[] args) throws InterruptedException {
        MainWindow mainWindow = new MainWindow();
    }
}
