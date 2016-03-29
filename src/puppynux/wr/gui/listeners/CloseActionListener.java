package puppynux.wr.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 24/02/16.
 */

public class CloseActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}