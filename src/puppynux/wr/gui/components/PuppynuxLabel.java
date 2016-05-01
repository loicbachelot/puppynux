package puppynux.wr.gui.components;

import puppynux.wr.gui.MainWindow;

import javax.swing.*;

/**
 * Created by william on 21/04/16.
 */
public class PuppynuxLabel extends JLabel {
    public PuppynuxLabel(String text) {
        super(text);
        this.setFont(MainWindow.font);
    }

    public PuppynuxLabel(String text, int position) {
        super(text, position);
        this.setFont(MainWindow.font);
    }

    public PuppynuxLabel(String text, int position, String type) {
        super(text, position);
        if (type.equals("title")) {
            this.setFont(MainWindow.titlePanelFont);
        }
        else if(type.equals("mainTitle")) {
            this.setFont(MainWindow.mainTitleFont);
        }
    }
}
