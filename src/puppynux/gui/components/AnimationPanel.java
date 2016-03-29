package puppynux.gui.components;

import javax.swing.*;

/**
 * Created by william on 17/03/16.
 * Transitionnal panel during first config
 */

public class AnimationPanel extends JPanel{

    JLabel loadingLabel;

    public AnimationPanel() {
        loadingLabel = new JLabel("", new ImageIcon(getClass().getClassLoader().getResource("resources/img/loading.gif")), JLabel.CENTER);
        this.add(loadingLabel);
    }
}
