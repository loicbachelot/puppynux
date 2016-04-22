package puppynux.gui.components;

import puppynux.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 17/03/16.
 * Contains MainWindow's right panel's elements
 */
public class NewsPanel extends JPanel {
    JLabel mainTitle;
    JLabel iterationLabel;
    JLabel locationLabel;
    JLabel actionLabel;
    JLabel rewardLabel;

    public NewsPanel() {
        setPreferredSize(new Dimension((int) MainWindow.windowSize().getWidth() / 3, getHeight()));
        setLayout(new GridLayout(0, 1));
        mainTitle = new JLabel("INFORMATIONS", JLabel.CENTER);
        mainTitle.setFont(MainWindow.font);
        locationLabel = new JLabel("", JLabel.CENTER);
        iterationLabel = new JLabel("", JLabel.CENTER);
        actionLabel = new JLabel("", JLabel.CENTER);
        rewardLabel = new JLabel("", JLabel.CENTER);
        add(mainTitle);
        add(iterationLabel);
        add(locationLabel);
        add(actionLabel);
        add(rewardLabel);
    }

    public JLabel getIterationLabel() {
        return iterationLabel;
    }

    public JLabel getActionLabel() {
        return actionLabel;
    }

    public JLabel getLocationLabel() {
        return locationLabel;
    }

    public JLabel getRewardLabel() {
        return rewardLabel;
    }
}
