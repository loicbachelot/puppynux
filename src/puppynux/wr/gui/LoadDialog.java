package puppynux.wr.gui;

import puppynux.wr.gui.components.PuppynuxButton;
import puppynux.wr.gui.data.Choices;
import puppynux.wr.gui.data.LoadDialogInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.wr.gui.
 * Dialog when user clicks on "load" button
 */
public class LoadDialog extends JDialog implements PuppyDialog {

    private LoadDialogInfo loadDialogInfo;
    private JTable table;
    private Object[][] elements;

    public LoadDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setResizable(false);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        initComponent();
        pack();
        setLocationRelativeTo(null);
    }

    private DefaultTableModel setContent() {
        int size = 10;
        ClassLoader classLoader = getClass().getClassLoader();
        String[] title = {"Agent's name", "Last played"};
        elements = new Object[size][];
        File backup = new File(classLoader.getResource("resources/backup/").getFile());
        int i = 0;
        for (String pathname :
                backup.list()) {
            File file = new File(classLoader.getResource("resources/backup/").getFile() + pathname);
            elements[i++] = new Object[]{pathname.split("\\.")[0], new Date(file.lastModified())};
        }
        return new DefaultTableModel(elements, title);
    }

    //TODO interaction with JTable
    public void initComponent() {
        JPanel contentPanel = new JPanel();
        table = new JTable(setContent());
//        table.setDefaultRenderer(JComponent.class, new PuppyTableCellRenderer());
        //table.getColumn("Open").setCellRenderer(new TableButtonRenderer());
        PuppynuxButton cancelButton = new PuppynuxButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initInfo();
                loadDialogInfo.setChoice(Choices.CANCEL);
                setVisible(false);
            }
        });
        PuppynuxButton okButton = new PuppynuxButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initInfo();
                loadDialogInfo.setChoice(Choices.OK);
                setVisible(false);
            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.add(okButton);
        controlPanel.add(cancelButton);

        contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
        getContentPane().add(contentPanel);
    }

    @Override
    public LoadDialogInfo showDialog() {
        setVisible(true);
        return loadDialogInfo;
    }

    @Override
    public void initInfo(int config) {

    }

    @Override
    public void initInfo() {
        if (table.getSelectedRow() == -1) {
            loadDialogInfo = new LoadDialogInfo(null);
            loadDialogInfo.setChoice(Choices.CANCEL);
            setVisible(false);
        }
        loadDialogInfo = new LoadDialogInfo((String) elements[table.getSelectedRow()][0]);
    }
}
