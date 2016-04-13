package puppynux.gui.components;

import puppynux.rg.GameEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 17/03/16.
 * Contains MainWindow's bottom panel's elements
 */
public class RewardsPanel extends JPanel {
    JSlider rewardSlider;
    JLabel sliderValue;
    JButton confirmButton, pauseButton;
    int reward;

    public RewardsPanel() {
        initComponent();
    }

    public void initComponent() { //TODO Bouton à l'échelle de la fenètre
        pauseButton = new JButton(new ImageIcon(new ImageIcon("src/resources/img/pauseButton.png").getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT)));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameEngine.getInstance().getAiManager().isLiving()) {
                    GameEngine.getInstance().getAiManager().pause();
                    pauseButton.setIcon(new ImageIcon(new ImageIcon("src/resources/img/playButton.png").getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT)));
                }
                else {
                    GameEngine.getInstance().getAiManager().play();
                    pauseButton.setIcon(new ImageIcon(new ImageIcon("src/resources/img/pauseButton.png").getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT)));
                }
            }
        });

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

        add(pauseButton);
        add(rewardSlider);
        add(sliderValue);
        add(confirmButton);
    }
}