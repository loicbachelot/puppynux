package puppynux.gui;

import puppynux.gui.data.Choices;
import puppynux.gui.data.ConfigDialogInfo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JLabel nameLabel, envLabel, learnSpeedLabel, refreshLabel, velocityLabel, noiseLabel, oversightLabel;
    private JTextField name;
    private JComboBox<String> environment;
    private JSlider learnSpeedSlider, refreshSlider, noiseSlider, oversightSlider;
    private JSpinner velocitySpinner;

    /**
     * @param parent Parent frame
     * @param title  Dialog's title
     * @param modal
     */
    public ConfigDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
//        setSize(550, 270);
        mainWindow = (MainWindow) parent;
//        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initComponent();
        pack();
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
        //Name
        JPanel agentPanel = new JPanel();
        agentPanel.setBorder(BorderFactory.createTitledBorder("Agent's attributes"));
        agentPanel.setLayout(new GridLayout(0, 1, 0, 10));
        nameLabel = new JLabel("Select name :", JLabel.CENTER);
        name = new JTextField();
        name.setColumns(12);
        JPanel agentContentPanel = new JPanel();
        agentContentPanel.add(nameLabel);
        agentContentPanel.add(name, JPanel.CENTER_ALIGNMENT);
        agentPanel.add(agentContentPanel);

        learnSpeedLabel = new JLabel("Learn spead : ", JLabel.CENTER);
        learnSpeedSlider = new JSlider();
        learnSpeedSlider.setMaximum(10);
        learnSpeedSlider.setMinimum(0);
        learnSpeedSlider.setValue(10);
        JLabel learnValueLabel = new JLabel(String.valueOf((double) learnSpeedSlider.getValue() / 10));
        learnSpeedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                learnValueLabel.setText(String.valueOf((double) learnSpeedSlider.getValue() / 10));
            }
        });
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(learnSpeedLabel);
        sliderPanel.add(learnSpeedSlider);
        sliderPanel.add(learnValueLabel);
        agentPanel.add(sliderPanel);

        refreshLabel = new JLabel("Refresh frequency : ", JLabel.CENTER);
        refreshSlider = new JSlider();
        refreshSlider.setMaximum(10);
        refreshSlider.setMinimum(0);
        refreshSlider.setValue(10);
        JLabel refreshValueLabel = new JLabel(String.valueOf((double) refreshSlider.getValue() / 10));
        refreshSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshValueLabel.setText(String.valueOf((double) refreshSlider.getValue() / 10));
            }
        });
        JPanel refreshSliderPanel = new JPanel();
        refreshSliderPanel.add(refreshLabel);
        refreshSliderPanel.add(refreshSlider);
        refreshSliderPanel.add(refreshValueLabel);
        agentPanel.add(refreshSliderPanel);

        velocityLabel = new JLabel("Velocity : ", JLabel.CENTER);
        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 10, 1);
        velocitySpinner = new JSpinner(spinnerModel);

        JPanel velocitySpinnerPanel = new JPanel();
        velocitySpinnerPanel.add(velocityLabel);
        velocitySpinnerPanel.add(velocitySpinner);
        agentPanel.add(velocitySpinnerPanel);

        oversightLabel = new JLabel("Oversight factor : ", JLabel.CENTER);
        oversightSlider = new JSlider();
        oversightSlider.setMaximum(10);
        oversightSlider.setMinimum(0);
        oversightSlider.setValue(10);
        JLabel oversightValueLabel = new JLabel(String.valueOf(oversightSlider.getValue()));
        oversightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                oversightValueLabel.setText(String.valueOf(oversightSlider.getValue()));
            }
        });
        JPanel oversightSliderPanel = new JPanel();
        oversightSliderPanel.add(oversightLabel);
        oversightSliderPanel.add(oversightSlider);
        oversightSliderPanel.add(oversightValueLabel);
        agentPanel.add(oversightSliderPanel);

        noiseLabel = new JLabel("Noise factor : ", JLabel.CENTER);
        noiseSlider = new JSlider();
        noiseSlider.setMaximum(10);
        noiseSlider.setMinimum(0);
        noiseSlider.setValue(10);
        JLabel noiseValueLabel = new JLabel(String.valueOf(noiseSlider.getValue()));
        noiseSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                noiseValueLabel.setText(String.valueOf(noiseSlider.getValue()));
            }
        });
        JPanel noiseSliderPanel = new JPanel();
        noiseSliderPanel.add(noiseLabel);
        noiseSliderPanel.add(noiseSlider);
        noiseSliderPanel.add(noiseValueLabel);
        agentPanel.add(noiseSliderPanel);

        JPanel envPanel = new JPanel();
        envPanel.setBorder(BorderFactory.createTitledBorder("Environment's attribute"));
        envLabel = new JLabel("Choose environement : ");
        String[] elements = {"Env1", "Env2"};
        environment = new JComboBox<>(elements);
        JButton edit = new JButton("Edit");
        envPanel.add(envLabel);
        envPanel.add(environment);
        envPanel.add(edit);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 2));
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
    public void initInfo() {
        configDialogInfo = new ConfigDialogInfo(name.getText(),
                environment.getSelectedIndex(), learnSpeedSlider.getValue(),
                refreshSlider.getValue(), (int) velocitySpinner.getValue(),
                oversightSlider.getValue(), noiseSlider.getValue());
    }

    /**
     * Allows to make ConfigDialog visible
     *
     * @param b
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    /**
     * @param sendData
     */
    @Override
    public void setSendData(boolean sendData) {
        this.sendData = sendData;
    }
}