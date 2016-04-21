package puppynux.gui;

import puppynux.gui.components.PuppynuxButton;
import puppynux.gui.components.PuppynuxLabel;
import puppynux.gui.data.IASettingDialogInfo;
import puppynux.gui.listeners.CancelListener;
import puppynux.gui.listeners.OKListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.
 * >
 */

public class IASettingDialog extends JDialog implements PuppyDialog {

    private IASettingDialogInfo iaSettingDialogInfo;
    private boolean sendData;
    private JPanel controlPanel, contentPanel;
    private PuppynuxLabel value;
    private JSlider slider; //TODO récupérer la dernière valeur et pas mettre valeur à 0


    public IASettingDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initComponent();
        pack();
        getContentPane().setBackground(new Color(68, 145, 247));
        setLocationRelativeTo(null);
    }

    private void initComponent() {
        //Name
        JPanel agentPanel = new JPanel();
        agentPanel.setBorder(BorderFactory.createTitledBorder("Agent's curiosity"));
        slider = new JSlider(0, 10, 0);
        value = new PuppynuxLabel(String.valueOf((double) slider.getValue() / 10));
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slider.getValueIsAdjusting()) {
                    value.setText(String.valueOf((double) slider.getValue() / 10));
                }
            }
        });

        agentPanel.add(slider);
        agentPanel.add(value);

        contentPanel = new JPanel();
        contentPanel.add(agentPanel);
        controlPanel = new JPanel();

        PuppynuxButton ok = new PuppynuxButton("OK");
        PuppynuxButton cancel = new PuppynuxButton("Cancel");
        cancel.addActionListener(new CancelListener(this));
        ok.addActionListener(new OKListener(this));
        controlPanel.add(ok);
        controlPanel.add(cancel);

        getContentPane().add(contentPanel, BorderLayout.NORTH);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void setSendData(boolean sendData) {
        this.sendData = sendData;
    }

    @Override
    public IASettingDialogInfo showDialog() {
        sendData = false;
        setVisible(true);
        return iaSettingDialogInfo;
    }

    @Override
    public void initInfo() {
        iaSettingDialogInfo = new IASettingDialogInfo(slider.getValue());
    }
}