package puppynux.gui.components;

import puppynux.rg.GameEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 17/03/16.
 * Contains MainWindow's bottom panel's elements
 */
public class RewardsPanel extends JPanel {
    JSlider rewardSlider;
    JLabel sliderValue;
    JButton confirmButton;
    int reward;

    public RewardsPanel() {
        initComponent();
    }

    public void initComponent() {
        rewardSlider = new JSlider(-100, 100, 0);
        sliderValue = new JLabel(String.valueOf(rewardSlider.getValue()));
        rewardSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rewardSlider.getValueIsAdjusting()) {
                    sliderValue.setText(String.valueOf(rewardSlider.getValue()));
                }
            }
        });

        confirmButton = new JButton("OK");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reward = rewardSlider.getValue();
                GameEngine.getInstance().attributeReward(reward);
//                GameEngine.getInstance().getAiManager().getAgent().attributeReward(reward);
            }
        });
        add(rewardSlider);
        add(sliderValue);
        add(confirmButton);
    }
}