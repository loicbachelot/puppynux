package puppynux.wr.gui;

import puppynux.wr.gui.components.PuppynuxButton;
import puppynux.wr.gui.data.Choices;
import puppynux.wr.gui.data.FirstDialogInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by william on 09/03/16.
 * Dialog for game's first choice "Load" or "New"
 */
public class FirstDialog extends JDialog implements PuppyDialog {

    private FirstDialogInfo firstDialogInfo;
    private PuppynuxButton loadButton, newButton, statsButton;
    private JPanel controlPanel;

    /**
     * @param parent Parent frame
     * @param title  FirstDialog's title
     * @param modal
     */
    public FirstDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponent();
        pack();
        this.getContentPane().setBackground(new Color(68, 145, 247));
        setLocationRelativeTo(null);
    }

    /**
     * //TODO JavaDoc
     *
     * @return
     */
    public FirstDialogInfo showDialog() {
        setVisible(true);
        return firstDialogInfo;
    }

    @Override
    public void initInfo() {

    }

    @Override
    public void initInfo(int config) {

    }

    private void initComponent() {
        controlPanel = new JPanel();
        newButton = new PuppynuxButton("New");
        loadButton = new PuppynuxButton("Load");
        statsButton = new PuppynuxButton("Stats");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                firstDialogInfo = new FirstDialogInfo(Choices.CANCEL);
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstDialogInfo = new FirstDialogInfo(Choices.NEW);
                setVisible(false);
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                firstDialogInfo = new FirstDialogInfo(Choices.LOAD);
                setVisible(false);
            }
        });
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstDialogInfo = new FirstDialogInfo(Choices.STATS);
                setVisible(false);
            }
        });

        controlPanel.add(newButton);
        controlPanel.add(loadButton);
        controlPanel.add(statsButton);
//        add(controlPanel);
        getContentPane().add(controlPanel);
    }

}
