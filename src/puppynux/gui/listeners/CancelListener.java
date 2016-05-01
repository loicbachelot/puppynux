package puppynux.gui.listeners;

import puppynux.gui.PuppyDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 10/03/16.
 * Listener for "cancel" buttons
 */
public class CancelListener implements ActionListener {

    private PuppyDialog dialog;

    /**
     * @param dialog
     */
    public CancelListener (PuppyDialog dialog) {
        this.dialog = dialog;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.setSendData(false);
        dialog.setVisible(false);
    }
}
