package puppynux.gui.components;

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
    //JLabel label= new JLabel("DES INFOS...");
    JSlider rewardSlider;
    JLabel sliderValue;
    JButton confirmButton;

    public RewardsPanel() {
        //add(label);
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

            }
        });
        add(rewardSlider);
        add(sliderValue);
        add(confirmButton);
    }
}
