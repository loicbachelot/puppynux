package puppynux.wr.gui.listeners;

import puppynux.rg.AI.mock.Observer;
import puppynux.wr.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 02/03/16.
 */

public class FirstDialog extends JDialog{

    JPanel mainPanel = new JPanel();
    Observer observer;
    ParametersDialog parametersDialog = new ParametersDialog(null, "IA Parameters", true);
    private JButton loadButton = new JButton("Load");
    private JButton newButton = new JButton("New");

    public FirstDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setSize(Window.windowWidth / 5, Window.windowHeight / 10);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public void showDialog() {
        this.setVisible(true);
    }
    public void showDialog(Observer observer) {
        this.observer = observer;
        this.setVisible(true);
    }

    private void initComponent() {

        //Name
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parametersDialog.showDialog(observer);
            }
        });
        mainPanel.add(newButton);
        mainPanel.add(loadButton);

        this.getContentPane().add(mainPanel);

    }
}
