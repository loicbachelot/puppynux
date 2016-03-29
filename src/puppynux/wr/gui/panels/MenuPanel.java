
package puppynux.wr.gui.panels;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import puppynux.wr.gui.Window;

/**
 * Created by william on 20/02/16.
 */

public class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    CenterPanel centerPanel = new CenterPanel();
    JPanel southPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private Dimension size = getPreferredSize();

    public MenuPanel() {
        size.width = Window.windowWidth / 4;
        setPreferredSize(size);
        initLayout();
    }

    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        southPanel.add(new JLabel("Test"));
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }
}