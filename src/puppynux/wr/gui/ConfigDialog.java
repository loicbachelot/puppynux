package puppynux.wr.gui;

import puppynux.wr.gui.components.PuppynuxButton;
import puppynux.wr.gui.components.PuppynuxLabel;
import puppynux.wr.gui.data.Choices;
import puppynux.wr.gui.data.ConfigDialogInfo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by william on 09/03/16.
 * Dialog for game's configurations
 */
public class ConfigDialog extends JDialog implements PuppyDialog {

    private ConfigDialogInfo configDialogInfo;
    private boolean sendData;
    private JPanel controlPanel, contentPanel;
    private PuppynuxLabel nameLabel, envLabel;
    private JTextField name;
    private JComboBox<String> environment;
    private JRadioButton radioButtonConfig1, radioButtonConfig2, radioButtonConfig3;
    private ButtonGroup bg;

    /**
     * @param parent Parent frame
     * @param title  Dialog's title
     * @param modal
     */
    public ConfigDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initComponent();
        pack();
        setBackground(new Color(68, 145, 247));
        setLocationRelativeTo(null);
    }

    /**
     * Allows to show ConfigDialog
     *
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

        PuppynuxButton ok = new PuppynuxButton("OK");
        ok.setEnabled(false);

        //Name
        JPanel agentPanel = new JPanel();
        agentPanel.setBorder(BorderFactory.createTitledBorder("Agent's attributes")); //TODO Configurations prédéfinies pour utilisateur lambda
        agentPanel.setLayout(new GridLayout(0, 1, 0, 10));
        nameLabel = new PuppynuxLabel("Select name :", PuppynuxLabel.CENTER);
        name = new JTextField();
        name.setColumns(12);
        JPanel agentContentPanel = new JPanel();
        agentContentPanel.add(nameLabel);
        agentContentPanel.add(name, JPanel.CENTER_ALIGNMENT);
        agentPanel.add(agentContentPanel);
        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (name.getText().length() > 12 || name.getText().equals("")) {
                    ok.setEnabled(false);
                } else if (!name.getText().equals("")) {
                    ok.setEnabled(true);
                }
            }
        });

        JPanel configPanel = new JPanel();
        radioButtonConfig1 = new JRadioButton("Bulldog");
        radioButtonConfig2 = new JRadioButton("Bichon");
        radioButtonConfig3 = new JRadioButton("Collet");
        bg = new ButtonGroup();
        bg.add(radioButtonConfig1);
        bg.add(radioButtonConfig2);
        bg.add(radioButtonConfig3);
        configPanel.add(radioButtonConfig1);
        configPanel.add(radioButtonConfig2);
        configPanel.add(radioButtonConfig3);
        radioButtonConfig1.setSelected(true);
        agentPanel.add(configPanel);

        JPanel envPanel = new JPanel();
        envPanel.setBorder(BorderFactory.createTitledBorder("Environment's attribute"));
        envLabel = new PuppynuxLabel("Choose environement : ");
        String[] elements = {"Rich", "Simple"};
        environment = new JComboBox<>(elements);
        envPanel.add(envLabel);
        envPanel.add(environment);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 2));
        contentPanel.add(agentPanel);
        contentPanel.add(envPanel);

        controlPanel = new JPanel();
        PuppynuxButton cancel = new PuppynuxButton("Cancel");
        ok.addActionListener(e -> {
                    setVisible(false);
                    sendData = true;
                    initInfo(getSelectedConfig(bg));
                    configDialogInfo.setChoice(Choices.OK);
                }
        );
        cancel.addActionListener(e -> {
                    configDialogInfo = new ConfigDialogInfo(Choices.CANCEL);
                    sendData = false;
                    setVisible(false);
                }
        );
        controlPanel.add(ok);
        controlPanel.add(cancel);

        getContentPane().add(contentPanel, BorderLayout.NORTH);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);

    }

    /**
     * @param buttonGroup
     * @return selected config
     */
    public int getSelectedConfig(ButtonGroup buttonGroup) {
        int config = 0;
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                if (button.getText().equals(radioButtonConfig1.getText())) {
                    config = 1;
                } else if (button.getText().equals(radioButtonConfig2.getText())) {
                    config = 2;
                } else if (button.getText().equals(radioButtonConfig3.getText())) {
                    config = 3;
                }
            }
        }
        return config;
    }

    /**
     * @return selected environment
     */
    public String getSelectedEnvironment() {
        String env = "";
        String selected = environment.getItemAt(environment.getSelectedIndex());
        switch (selected) {
            case "Rich":
                env = "env.xml";
                break;
            case "Simple":
                env = "env2.xml";
                break;
        }
        return env;
    }

    /**
     * Initializes info to send to ConfigDialogInfo
     */
    @Override
    public void initInfo(int config) {
        String env = getSelectedEnvironment();
        if (config == 1) {
            configDialogInfo = new ConfigDialogInfo(name.getText(),
                    env, 0.2, 0.3, 0, 3, 2000);
        } else if (config == 2) {
            configDialogInfo = new ConfigDialogInfo(name.getText(),
                    env, .4, .6, 10, 6, 1500);
        } else if (config == 3) {
            configDialogInfo = new ConfigDialogInfo(name.getText(),
                    env, .8, .9, 8, 10, 1000);
        }
    }

    @Override
    public void initInfo() {

    }
}