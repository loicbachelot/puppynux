package puppynux.gui.components;

import puppynux.gui.MainWindow;
import puppynux.rg.GameEngine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
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
    PuppynuxLabel sliderValue;
    PuppynuxButton confirmButton, pauseButton;
    int reward;

    public RewardsPanel() {
        initComponent();
        setBackground(MainWindow.backgroundsColor);
        Border border = new MatteBorder(5, 0, 0, 0, MainWindow.bordersColor);
        setBorder(border);
    }

    public void initComponent() { //TODO Bouton à l'échelle de la fenètre
        pauseButton = new PuppynuxButton(new ImageIcon(new ImageIcon("src/resources/img/pauseButton.png").getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT)));
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
        sliderValue = new PuppynuxLabel(String.valueOf(rewardSlider.getValue()));
        rewardSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rewardSlider.getValueIsAdjusting()) {
                    sliderValue.setText(String.valueOf(rewardSlider.getValue()));
                }
            }
        });

        confirmButton = new PuppynuxButton("OK");
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