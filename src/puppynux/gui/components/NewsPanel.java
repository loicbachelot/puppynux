package puppynux.gui.components;

import log.LoggerUtility;
import org.apache.log4j.Logger;
import puppynux.rg.GameEngine;

import javax.swing.*;

/**
 * Created by william on 17/03/16.
 * Contains MainWindow's right panel's elements
 */
public class NewsPanel extends JPanel {
    JLabel label = new JLabel("INFOS CÔTÉ");
    JLabel debugLabel;

    public NewsPanel() {
        debugLabel = new JLabel();
        //add(label);
        add(debugLabel);
    }

    public JLabel getDebugLabel() {
        return debugLabel;
    }
}
