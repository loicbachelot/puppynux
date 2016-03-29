package puppynux.gui;

import puppynux.rg.GameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by niamor972 on 09/03/16.
 * Parts of puppynux.gui.
 * >
 */
public class MenuBar extends JMenuBar {
    private MainWindow mainWindow;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initComponent();
    }

    private void initComponent() {
        //File Menu
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        JMenuItem create = new JCheckBoxMenuItem("New");
        create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.showConfigDialog();
            }
        });
        file.add(create);
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.showLoadDialog();
            }
        });
        file.add(open);
        JMenuItem close = new JMenuItem("Close");
        //TODO stop closing system but current agent instance
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GameEngine.getInstance().getAiManager().kill();
            }
        });
        file.add(close);
        add(file);

        //Edit Menu
        JMenu edit = new JMenu("Edit");
        JMenuItem set = new JMenuItem("Set IA");
        //TODO link set with IASettingDialog
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mainWindow.showIASettingDialog();
            }
        });
        edit.add(set);
        add(edit);

        JMenu history = new JMenu("History");
        JMenuItem opened = new JMenuItem("Opened...");
        history.add(opened);
        add(history);
    }
}
