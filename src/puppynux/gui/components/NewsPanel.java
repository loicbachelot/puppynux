package puppynux.gui.components;

import puppynux.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
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
        setPreferredSize(new Dimension((int)MainWindow.windowSize().getWidth() / 3, (int)MainWindow.windowSize().getHeight()));
        setLayout(new GridLayout(0, 1));
        mainTitle = new JLabel("INFORMATION", JLabel.CENTER);
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
        setBackground(new Color(174, 215, 247));
        Border border = new MatteBorder(0, 5, 0, 0, new Color(50, 50, 50));
        setBorder(border);
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
