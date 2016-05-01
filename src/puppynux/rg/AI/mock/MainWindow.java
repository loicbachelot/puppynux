package puppynux.rg.AI.mock;/*
package puppynux.rg.AI.mock;

import config.Config;
import org.apache.log4j.Logger;
import puppynux.wr.gui.ConfigDialog;
import puppynux.wr.gui.FirstDialog;
import puppynux.wr.gui.IASettingDialog;
import puppynux.wr.gui.LoadDialog;
import puppynux.wr.gui.components.*;
import puppynux.wr.gui.data.*;
import puppynux.rg.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

*/
/**
 * Created by niamor972 on 09/03/16.
 * Parts of puppynux.wr.gui.
 * >
 *//*

public class MainWindow extends JFrame implements Observer {

    private final static Logger logger = Logger.getLogger(MainWindow.class);
    private Dashboard dashboard;
    private BackgroundPanel backgroundPanel;
    private RewardsPanel rewardsPanel;
    private FirstDialogInfo firstDialogInfo;
    private ConfigDialogInfo configDialogInfo;
    private LoadDialogInfo loadDialogInfo;
    private IASettingDialogInfo iaSettingDialogInfo;
    private MenuBar menuBar;
    private BorderLayout borderLayout;
    private AnimationPanel animation;
    private NewsPanel newsPanel;
    private volatile boolean go;

    public MainWindow() throws InterruptedException {
        GameEngine.getInstance().addObserver("mainWindow", this);
        dashboard = ComponentFactory.initDashboard();
        rewardsPanel = ComponentFactory.initRewardsPanel();
        newsPanel = ComponentFactory.initNewsPanel();
        backgroundPanel = ComponentFactory.initBackgroundPanel();
//        menuBar = ComponentFactory.initMenuBar(this);
        animation = ComponentFactory.initAnimationPanel();
        borderLayout = new BorderLayout();
        this.setTitle("Puppynux");
        this.setSize(windowSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        this.setJMenuBar(menuBar);
        this.add(animation);
        this.setContentPane(getContentPane());
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("test closing");
                dispose();
                go = false;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("test closed");
            }
        });


        go();
    }
    */
/*
        @Override
        public void update(int[] coor) {
            dashboard.getObject().setX(coor[0]);
            dashboard.getObject().setY(coor[1]);
            dashboard.drawObjects();
        }
    *//*

    //// TODO: 23/03/16 make update only change main window state, and switch state in it to generate actions
    //in order to be more thread safe
    public void go() throws InterruptedException {
        go = true;

        showFirstDialog();
        animation.setVisible(false);
        add(dashboard, BorderLayout.CENTER);
        add(rewardsPanel, BorderLayout.SOUTH);
        add(newsPanel, BorderLayout.EAST);
        if (firstDialogInfo.getInfo().equals(Choices.NEW)) {
            showConfigDialog();
            if (configDialogInfo.getChoice().equals(Choices.OK)) {
                GameEngine.getInstance().createAgent(null);
            }
        } else if (firstDialogInfo.getInfo().equals(Choices.LOAD)) {
            showLoadDialog();
        } else {
            JOptionPane.showMessageDialog(null, "No session launched", "WARN", JOptionPane.INFORMATION_MESSAGE);
        }

        while (go) {
            Thread.sleep(0L, 1);
        }
    }

    public static Dimension windowSize() {
        Config config = null;
        config = config.getInstance();
*/
/*
        try {
            config.load("src/config/config.json");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*//*


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
        dashboard.getObject().setX(state%4);
        dashboard.getObject().setY(state/4);
        dashboard.drawObjects();
        repaint();
        logger.trace("[WINDOW] updated");
    }

    @Override
    public void update() {

    }
}
*/
