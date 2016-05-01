package puppynux.wr.gui.components;

import puppynux.wr.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 17/03/16.
 * Transitionnal panel during first config
 */

public class AnimationPanel extends JPanel{

    private JLabel loadingLabel;
    private PuppynuxLabel mainTitleLabel;

    public AnimationPanel() {
        mainTitleLabel = new PuppynuxLabel("Welcome to PUPPYNUX", JLabel.CENTER, "mainTitle");
        loadingLabel = new JLabel("", new ImageIcon(getClass().getClassLoader().getResource("resources/img/animation.gif")), JLabel.CENTER);
        this.setLayout(new GridLayout(0,1));
        this.add(mainTitleLabel);
        this.add(loadingLabel);
        setBackground(MainWindow.backgroundsColor);
    }
}
