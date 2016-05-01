package puppynux.wr.gui.listeners;

import puppynux.wr.gui.PuppyDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 10/03/16.
 * Listener for "ok" buttons
 */
public class OKListener implements ActionListener {

    private PuppyDialog dialog;

    /**
     *
     * @param dialog
     */
    public OKListener (PuppyDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.setSendData(true);
        dialog.initInfo();
        dialog.setVisible(false);
    }
}
