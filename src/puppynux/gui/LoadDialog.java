package puppynux.gui;

import puppynux.gui.components.PuppyTableCellRenderer;
import puppynux.gui.components.PuppyTableModel;
import puppynux.gui.components.TableButtonRenderer;
import puppynux.gui.data.Choices;
import puppynux.gui.data.FirstDialogInfo;
import puppynux.gui.data.LoadDialogInfo;
import puppynux.gui.listeners.CancelListener;
import puppynux.gui.listeners.OKListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.gui.
 * >
 */
public class LoadDialog extends JDialog implements PuppyDialog {

    private boolean sendData;
    private LoadDialogInfo loadDialogInfo;

    public LoadDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initComponent();
        pack();
    }

    public PuppyTableModel getNewLine() {
        Object[][] elements = {{"Test", "Test", "0", new JButton("Open")},
                {"Foo", "Bar", "42", new JButton("Open")}};
        String[] title = {"Agent's name", "Environment", "Last played", "Open"};
        return new PuppyTableModel(elements, title);
        //return new DefaultTableModel(elements, title);
    }

    //TODO interaction with JTable
    public void initComponent() {
        JPanel contentPanel = new JPanel();
        JTable table = new JTable(getNewLine());
        table.setDefaultRenderer(JComponent.class, new PuppyTableCellRenderer());
        //table.getColumn("Open").setCellRenderer(new TableButtonRenderer());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDialogInfo = new LoadDialogInfo(Choices.CANCEL);
                sendData = false;
                setVisible(false);

            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.add(cancelButton);

        contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
        getContentPane().add(contentPanel);
    }

    @Override
    public void setSendData(boolean sendData) {
        this.sendData = sendData;
    }

    @Override
    public LoadDialogInfo showDialog() {
        sendData = false;
        setVisible(true);
        return loadDialogInfo;
    }

    @Override
    public void initInfo() {
        if (!sendData) {
            loadDialogInfo = new LoadDialogInfo(Choices.CANCEL);
        } else {
            loadDialogInfo = new LoadDialogInfo(Choices.OPEN);
        }
    }
}
