package puppynux.wr.gui.components;

import puppynux.rg.AI.actions.Action;
import puppynux.rg.GameEngine;
import puppynux.wr.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by william on 17/03/16.
 * Contains MainWindow's bottom panel's elements
 */
public class RewardsPanel extends JPanel {
    private JSlider rewardSlider;
    private PuppynuxLabel rewardLabel;
    private JTextArea sliderValue;
    private PuppynuxButton confirmButton, pauseButton, forceActionButton;
    private JComboBox<Action> actionComboBox;
    private int reward;

    public RewardsPanel() {
        setLayout(new GridLayout(1, 0));
        initComponent();
        setBackground(MainWindow.backgroundsColor);
        Border border = new MatteBorder(5, 0, 0, 0, MainWindow.bordersColor);
        setBorder(border);
    }

    public void initComponent() { //TODO Bouton à l'échelle de la fenètre
        pauseButton = new PuppynuxButton(new ImageIcon(new ImageIcon("src/resources/img/playButton.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameEngine.getInstance().getAiManager().isActing()) {
                    GameEngine.getInstance().getAiManager().pause();
                    pauseButton.setIcon(new ImageIcon(new ImageIcon("src/resources/img/playButton.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                } else {
                    GameEngine.getInstance().getAiManager().play();
                    pauseButton.setIcon(new ImageIcon(new ImageIcon("src/resources/img/pauseButton.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                }
            }
        });

        JPanel rewardPanel = new JPanel();
        rewardLabel = new PuppynuxLabel("Reward : ");

        rewardSlider = new JSlider(-100, 100, 0);
        sliderValue = new JTextArea(String.valueOf(rewardSlider.getValue()), 1, 3);
        sliderValue.setEditable(false);
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
            }
        });
        rewardPanel.add(rewardLabel);
        rewardPanel.add(rewardSlider);
        rewardPanel.add(sliderValue);
        rewardPanel.add(confirmButton);

        JPanel forceActionPanel = new JPanel();

        actionComboBox = new JComboBox<>();
        forceActionButton = new PuppynuxButton("Force action");
        forceActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameEngine.getInstance().forceAct((Action)actionComboBox.getSelectedItem());
            }
        });
        forceActionPanel.add(actionComboBox);
        forceActionPanel.add(forceActionButton);

        add(pauseButton);
        add(rewardPanel);
        add(forceActionPanel);
    }

    public void setJComboBox (ArrayList<Action> list) {
        actionComboBox.removeAllItems();
        for (Action action :
                list) {
            actionComboBox.addItem(action);
        }
    }
}