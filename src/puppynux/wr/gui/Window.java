package puppynux.wr.gui;


import puppynux.rg.AI.mock.Observer;
import puppynux.wr.gui.listeners.FirstDialog;
import puppynux.wr.gui.objects.AI;
import puppynux.wr.gui.objects.Dog;
import puppynux.wr.gui.panels.AIPanel;
import puppynux.wr.gui.panels.MenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 10/02/16.
 */

public class Window extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    public static JPanel mainPanel = new JPanel();
    public static AI ai = new Dog(1, 0);
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int windowWidth = screenSize.width;
    public static int windowHeight = screenSize.height;
    FirstDialog firstDialog = new FirstDialog(null, "Welcome", true);
    private MenuBar menu = new MenuBar();
    private AIPanel aiPanel = new AIPanel(ai, 10);
    private BorderLayout borderLayout = new BorderLayout();

    public Window() {
        super("Test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(menu.getMenuBar());
        mainPanel.setLayout(borderLayout);
        mainPanel.add(aiPanel, BorderLayout.CENTER);
        this.setContentPane(mainPanel);
        //mainPanel.add(menuPanel, BorderLayout.EAST);
        this.setVisible(true);
        firstDialog.showDialog(this);
    }

    public static JPanel getMainPanel() {
        return mainPanel;
    }

    public static AI getAi() {
        return ai;
    }

    /*
        @Override
        public void update(int[] coor) {
            ai.setX(coor[0]);
            ai.setY(coor[1]);
            repaint();
        }


        public void update(int[] coor, Thread t) {
            update(coor);
            t.start();
        }
    */
    @Override
    public void update(int state) {

    }

    @Override
    public void update(String placePosition, String subplacePosition) {

    }

}