package puppynux.wr.gui;

import puppynux.wr.gui.components.PuppynuxButton;
import puppynux.wr.gui.components.PuppynuxLabel;
import puppynux.wr.gui.data.Choices;
import puppynux.wr.gui.data.IASettingDialogInfo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.
 * >
 */

public class IASettingDialog extends JDialog implements PuppyDialog {

    private IASettingDialogInfo iaSettingDialogInfo;
    private JButton okButton, cancelButton;
    private PuppynuxLabel learnSpeedLabel, refreshLabel, velocityLabel, noiseLabel, oversightLabel;
    private JSlider learnSpeedSlider, refreshSlider, noiseSlider, oversightSlider;
    private JSpinner velocitySpinner;


    public IASettingDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponent();
        pack();
        getContentPane().setBackground(new Color(68, 145, 247));
        setLocationRelativeTo(null);
    }

    private void initComponent() {
        JPanel agentPanel = new JPanel();
        agentPanel.setBorder(BorderFactory.createTitledBorder("Agent's attributes")); //TODO Configurations prédéfinies pour utilisateur lambda
        learnSpeedLabel = new PuppynuxLabel("Learn speed : ", PuppynuxLabel.CENTER);
        learnSpeedSlider = new JSlider();
        learnSpeedSlider.setMaximum(10);
        learnSpeedSlider.setMinimum(0);
        learnSpeedSlider.setValue(5);
        PuppynuxLabel learnValueLabel = new PuppynuxLabel(String.valueOf((double) learnSpeedSlider.getValue() / 10));
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

        refreshLabel = new PuppynuxLabel("Refresh frequency : ", PuppynuxLabel.CENTER);
        refreshSlider = new JSlider();
        refreshSlider.setMaximum(10);
        refreshSlider.setMinimum(0);
        refreshSlider.setValue(5);
        PuppynuxLabel refreshValueLabel = new PuppynuxLabel(String.valueOf((double) refreshSlider.getValue() / 10));
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

        velocityLabel = new PuppynuxLabel("Velocity : ", PuppynuxLabel.CENTER);
        SpinnerModel spinnerModel = new SpinnerNumberModel(5, 0, 10, 1);
        velocitySpinner = new JSpinner(spinnerModel);
        JPanel velocitySpinnerPanel = new JPanel();
        velocitySpinnerPanel.add(velocityLabel);
        velocitySpinnerPanel.add(velocitySpinner);
        agentPanel.add(velocitySpinnerPanel);

        oversightLabel = new PuppynuxLabel("Oversight factor : ", PuppynuxLabel.CENTER);
        oversightSlider = new JSlider();
        oversightSlider.setMaximum(10);
        oversightSlider.setMinimum(0);
        oversightSlider.setValue(5);
        PuppynuxLabel oversightValueLabel = new PuppynuxLabel(String.valueOf(oversightSlider.getValue()));
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

        noiseLabel = new PuppynuxLabel("Noise factor : ", PuppynuxLabel.CENTER);
        noiseSlider = new JSlider();
        noiseSlider.setMaximum(10);
        noiseSlider.setMinimum(0);
        noiseSlider.setValue(5);
        PuppynuxLabel noiseValueLabel = new PuppynuxLabel(String.valueOf(noiseSlider.getValue()));
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

        JPanel controlPanel = new JPanel();
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                initInfo();
                iaSettingDialogInfo.setChoice(Choices.OK);
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initInfo();
                setVisible(false);
            }
        });
        controlPanel.add(okButton);
        controlPanel.add(cancelButton);

        getContentPane().add(agentPanel, BorderLayout.NORTH);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public IASettingDialogInfo showDialog() {
        setVisible(true);
        return iaSettingDialogInfo;
    }

    @Override
    public void initInfo(int config) {

    }

    @Override
    public void initInfo() {
        iaSettingDialogInfo  = new IASettingDialogInfo(learnSpeedSlider.getValue() / 10,
                refreshSlider.getValue() / 10, (int) velocitySpinner.getValue(),
                oversightSlider.getValue(), noiseSlider.getValue() * 15);
    }
}