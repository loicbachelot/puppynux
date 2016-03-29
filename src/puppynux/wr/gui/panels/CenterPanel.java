package puppynux.wr.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 26/02/16.
 */

public class CenterPanel extends JPanel {

    JLabel debugLabel;
    JPanel buttonPanel = new JPanel();
    ImageIcon playIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/img/playButton.png"));
    JButton playButton;

    public CenterPanel() {
        this.setLayout(new BorderLayout());
        Image toScale = playIcon.getImage();
        Image scaledImage = toScale.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        playIcon = new ImageIcon(scaledImage);
        playButton = new JButton(playIcon);
        buttonPanel.add(playButton);
        debugLabel = new JLabel("Debug for IA... Coming soon");
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(debugLabel, BorderLayout.CENTER);
        //TODO other buttons and parameters
    }

    public JButton getPlayButton() {
        return playButton;
    }
}
