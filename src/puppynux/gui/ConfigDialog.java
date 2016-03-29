package puppynux.gui;

import puppynux.gui.data.Choices;
import puppynux.gui.data.ConfigDialogInfo;
import puppynux.gui.data.FirstDialogInfo;
import puppynux.gui.listeners.CancelListener;
import puppynux.gui.listeners.OKListener;
import puppynux.rg.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 09/03/16.
 * Dialog for game's configurations
 */
public class ConfigDialog extends JDialog implements PuppyDialog {

    private ConfigDialogInfo configDialogInfo;
    private MainWindow mainWindow;
    private boolean sendData;
    private JPanel controlPanel, contentPanel;
    private JLabel nameLabel, envLabel;
    private JTextField name;
    private JComboBox <String> environment;

    /**
     *
     * @param parent Parent frame
     * @param title Dialog's title
     * @param modal
     */
    public ConfigDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
//        setSize(550, 270);
        mainWindow = (MainWindow) parent;
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initComponent();
        pack();
    }

    /**
     * Allows to show ConfigDialog
     * @return Info managed by ConfigDialogInfo
     */
    public ConfigDialogInfo showDialog() {
        sendData = false;
        setVisible(true);
        return configDialogInfo;
    }

    /**
     * Initializes ConfigDialog's components
     */
    private void initComponent() {
        //Name
        JPanel agentPanel = new JPanel();
        agentPanel.setBorder(BorderFactory.createTitledBorder("Agent's attributes"));
        nameLabel = new JLabel("Select name :");
        name = new JTextField();
        name.setColumns(12);
        agentPanel.add(nameLabel);
        agentPanel.add(name);

        JPanel envPanel = new JPanel();
        envPanel.setBorder(BorderFactory.createTitledBorder("Environment's attribute"));
        envLabel = new JLabel("Choose environement : ");
        String [] elements = {"Env1", "Env2"};
        environment = new JComboBox<> (elements);
        JButton edit = new JButton("Edit");
        envPanel.add(envLabel);
        envPanel.add(environment);
        envPanel.add(edit);

        contentPanel = new JPanel();
        contentPanel.add(agentPanel);
        contentPanel.add(envPanel);

        controlPanel = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configDialogInfo = new ConfigDialogInfo(Choices.OK);
                setVisible(false);
                sendData = true;
                mainWindow.startGame(null);
//                GameEngine.getInstance().getAiManager().start();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configDialogInfo = new ConfigDialogInfo(Choices.CANCEL);
                sendData = false;
                setVisible(false);
            }
        });
        controlPanel.add(ok);
        controlPanel.add(cancel);

        getContentPane().add(contentPanel, BorderLayout.NORTH);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Initializes info to send to ConfigDialogInfo
     */
    @Override
    public void initInfo() {// throws Exception {
        configDialogInfo = new ConfigDialogInfo(name.getText(), environment.getSelectedIndex());
    }

    /**
     * Allows to make ConfigDialog visible
     * @param b
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    /**
     *
     * @param sendData
     */
    @Override
    public void setSendData(boolean sendData) {
        this.sendData = sendData;
    }
}