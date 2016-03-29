package puppynux.wr.gui.listeners;

import puppynux.rg.AI.mock.Observer;
import puppynux.rg.GameEngine;
import puppynux.wr.gui.Window;
import puppynux.wr.gui.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by william on 26/02/16.
 */

public class ParametersDialog extends JDialog {

    public static final String SAVE = System.getProperty("user.home") + "/Puppynux_Game/Data";
    Observer observer;
    MenuPanel menuPanel;
    String name;
    JLabel main;
    GameEngine gameEngine;
    boolean isPaused = true;
    private ArrayList<Object> sentGameData = new ArrayList<>();
    private JTextField nameField;
    private JComboBox<String> animalBox;
    private ArrayList<Object> receivedGameData = new ArrayList<>();

    public ParametersDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setSize(Window.windowWidth / 3, Window.windowHeight / 3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        gameEngine = new GameEngine();
        this.initComponent();
    }

    public void showDialog() {
        this.setVisible(true);
    }

    public void showDialog(Observer observer) {
        this.observer = observer;
//        gameEngine.addObserver(observer);
//        gameEngine.pause();
        this.setVisible(true);
    }

    private void initComponent() {

        //Name
        JPanel panName = new JPanel();
        panName.setBackground(Color.white);
        panName.setPreferredSize(new Dimension(220, 60));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 25));
        panName.setBorder(BorderFactory.createTitledBorder("IA's name"));
        JLabel nameLabel = new JLabel("Name :");
        panName.add(nameLabel);
        panName.add(nameField);

        //Animal TODO
        JPanel panAnimal = new JPanel();
        panAnimal.setBackground(Color.white);
        panAnimal.setPreferredSize(new Dimension(220, 60));
        animalBox = new JComboBox<String>();
        animalBox.addItem("Dog");
        animalBox.addItem("Cat");
        animalBox.addItem("TODO");
        animalBox.setPreferredSize(new Dimension(100, 25));
        panAnimal.setBorder(BorderFactory.createTitledBorder("Kind of animal"));
        JLabel kindLabel = new JLabel("Kind :");
        panAnimal.add(kindLabel);
        panAnimal.add(animalBox);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panName);
        content.add(panAnimal);

        JPanel control = new JPanel();
        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent arg0) {
                                           if (nameField.getText().equals("")) {
                                               JOptionPane jop = new JOptionPane();
                                               jop.showMessageDialog(null, "Please enter a valid nameField", "Error", JOptionPane.INFORMATION_MESSAGE);
                                           } else {
                                               sentGameData.add(nameField.getText());
                                               serializationSave();
                                               setVisible(false);
                                               menuPanel = new MenuPanel();
                                               name = (String) sentGameData.get(0);
                                               main = new JLabel(name + "'s stats");
                                               main.setHorizontalAlignment(JLabel.CENTER);
                                               main.setFont(new Font("Arial", Font.BOLD, 30));
                                               menuPanel.getNorthPanel().add(main);
                                               menuPanel.getCenterPanel().getPlayButton().addActionListener(new PlayButton());
                                               Window.mainPanel.add(menuPanel, BorderLayout.EAST);
                                               Window.mainPanel.revalidate();

                                               gameEngine.start();
                                           }
//                                           if (animalBox.getSelectedItem().equals("Dog"))
//                                               Window.ai = new Dog(1, 1);
//                                           if (animalBox.getSelectedItem().equals("Cat"))
//                                               Window.ai = new Cat(1, 1);
//                                           Window.mainPanel.revalidate();

                                       }
                                   }

        );

        //Cancel action
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent arg0) {
                                               setVisible(false);
                                           }
                                       }

        );

        control.add(okButton);
        control.add(cancelButton);

        this.

                getContentPane()

                .

                        add(content, BorderLayout.CENTER);

        this.

                getContentPane()

                .

                        add(control, BorderLayout.SOUTH);

    }

    public void serializationSave() {
        try {
            File save = new File(SAVE);
            save.mkdirs();
            FileOutputStream fos = new FileOutputStream(SAVE + "/save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getSentGameData());
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void serializationRead() {
        try {
            FileInputStream fis = new FileInputStream(ParametersDialog.SAVE + "/save.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            setReceivedGameData((ArrayList<Object>) ois.readObject());
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setAiName(String newName) {
        this.name = newName;
    }

    public ArrayList<Object> getReceivedGameData() {
        return receivedGameData;
    }

    public void setReceivedGameData(ArrayList<Object> receivedGameData) {
        this.receivedGameData = receivedGameData;
    }

    public Object getSentGameData() {
        return sentGameData;
    }

    class PlayButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPaused) {
//                gameEngine.suspend();
                isPaused = false;
            } else {
//                gameEngine.resume();
                isPaused = true;
            }
        }
    }
}