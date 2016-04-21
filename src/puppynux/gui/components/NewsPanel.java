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
    PuppynuxLabel mainTitle;
    PuppynuxLabel iterationLabel;
    PuppynuxLabel locationLabel;
    PuppynuxLabel actionLabel;
    PuppynuxLabel rewardLabel;

    public NewsPanel() {
        setPreferredSize(new Dimension((int)MainWindow.windowSize().getWidth() / 3, (int)MainWindow.windowSize().getHeight()));
        setLayout(new GridLayout(0, 1));
        mainTitle = new PuppynuxLabel("INFORMATION", PuppynuxLabel.CENTER, "title");
        locationLabel = new PuppynuxLabel("", PuppynuxLabel.CENTER, "");
        iterationLabel = new PuppynuxLabel("", PuppynuxLabel.CENTER, "");
        actionLabel = new PuppynuxLabel("", PuppynuxLabel.CENTER, "");
        rewardLabel = new PuppynuxLabel("", PuppynuxLabel.CENTER, "");
        add(mainTitle);
        add(iterationLabel);
        add(locationLabel);
        add(actionLabel);
        add(rewardLabel);
        setBackground(MainWindow.backgroundsColor);
        Border border = new MatteBorder(0, 5, 0, 0, MainWindow.bordersColor);
        setBorder(border);
    }

    public PuppynuxLabel getIterationLabel() {
        return iterationLabel;
    }

    public PuppynuxLabel getActionLabel() {
        return actionLabel;
    }

    public PuppynuxLabel getLocationLabel() {
        return locationLabel;
    }

    public PuppynuxLabel getRewardLabel() {
        return rewardLabel;
    }
}
