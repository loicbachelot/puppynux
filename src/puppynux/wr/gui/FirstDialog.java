package puppynux.wr.gui;

import puppynux.wr.gui.components.PuppynuxButton;
import puppynux.wr.gui.data.Choices;
import puppynux.wr.gui.data.FirstDialogInfo;
import puppynux.wr.gui.listeners.OKListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 09/03/16.
 * Dialog for game's first choice "Load" or "New"
 */
public class FirstDialog extends JDialog implements PuppyDialog {

    private boolean sendData;
    private FirstDialogInfo firstDialogInfo;
    private PuppynuxButton loadButton, newButton, cancelButton;
    private JPanel controlPanel;

    /**
     * @param parent Parent frame
     * @param title  FirstDialog's title
     * @param modal
     */
    public FirstDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
        sendData = false;
        setVisible(true);
        return firstDialogInfo;
    }

    @Override
    public void initInfo() {
        if (!sendData) {
            firstDialogInfo = new FirstDialogInfo(Choices.CANCEL);
        } else {
            firstDialogInfo = new FirstDialogInfo(Choices.NEW);
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    public boolean getSendData() {
        return sendData;
    }

    @Override
    public void setSendData(boolean sendData) {
        this.sendData = sendData;
    }

    private void initComponent() {
        controlPanel = new JPanel();
        newButton = new PuppynuxButton("New");
        loadButton = new PuppynuxButton("Load");
        cancelButton = new PuppynuxButton("Cancel");

        newButton.addActionListener(new OKListener(this));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                firstDialogInfo = new FirstDialogInfo(Choices.LOAD);
                sendData = true;
                setVisible(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstDialogInfo = new FirstDialogInfo(Choices.CANCEL);
                sendData = false;
                setVisible(false);
            }
        });

        controlPanel.add(newButton);
        controlPanel.add(loadButton);
        controlPanel.add(cancelButton);
//        add(controlPanel);
        getContentPane().add(controlPanel);
    }

}
