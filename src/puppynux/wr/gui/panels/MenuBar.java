package puppynux.wr.gui.panels;

import puppynux.wr.gui.listeners.CloseActionListener;
import puppynux.wr.gui.listeners.ParametersDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 20/02/16.
 */

public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private JMenuBar menuBar;

    public MenuBar() {
        menuBar = new JMenuBar();

        //File Menu
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(new CloseActionListener());
        file.add(close);
        menuBar.add(file);

        //Edit Menu
        JMenu edit = new JMenu("Edit");
        JMenuItem set = new JMenuItem("Set IA");
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ParametersDialog parametersDialog = new ParametersDialog(null, "IA Parameters", true);
                parametersDialog.showDialog();
            }
        });
        edit.add(set);
        menuBar.add(edit);

        JMenu history = new JMenu("History");
        JMenuItem opened = new JMenuItem("Opened...");
        history.add(opened);
        menuBar.add(history);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
