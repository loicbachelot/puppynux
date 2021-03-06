package puppynux.wr.gui.components;

import puppynux.lb.env.objects.Cell;
import puppynux.rg.GameEngine;
import puppynux.wr.gui.objects.Ball;
import puppynux.wr.gui.objects.Objects;
import puppynux.wr.gui.objects.Table;
import puppynux.wr.gui.objects.doors.PlaceLeftDoor;
import puppynux.wr.gui.objects.doors.PlaceRightDoor;
import puppynux.wr.gui.objects.doors.SubplaceDownDoor;
import puppynux.wr.gui.objects.doors.SubplaceTopDoor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 09/03/16.
 * Contains MainWindow's dashboard's elements
 */
public class Dashboard extends BackgroundPanel {

    private Objects animal;
    private Objects objects;
    private Graphics g;
    private boolean button = false;
    private JButton debug = new JButton("Debug");
    private volatile String placePosition = "";
    private volatile String subplacePosition = "";
    private int state = -1;

    /**
     * @param animal Animal initialized
     */
    public Dashboard(Objects animal, int dim) {
        super(dim);
        this.animal = animal;
        debug.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button)
                            button = false;
                        else
                            button = true;
                        repaint();
                        GameEngine.getInstance().setDebug(button);
                    }
                }
        );
        add(debug);
    }

    /**
     * @return Dashboard's animal
     */
    public Objects getAnimal() {
        return animal;
    }

    public Objects getObjects() {
        return objects;
    }

    @Override
    protected void environmentCase() {
        String sub = "";
        switch (subplacePosition) {
            case "Grass":
                sub = "grass";
                break;
            case "Kitchen":
                sub = "kitchen";
                break;
            case "LivingRoom":
                sub = "roof";
                break;
            case "Road":
                sub = "road";
                break;
        }
        String mainPath = "resources/img/" + sub;
        String pathCenter = mainPath + "Center.png";
        String pathBorder = mainPath + "Border.png";
        backgroundCenter = new ImageIcon(getClass().getClassLoader().getResource(pathCenter));
        backCenter = backgroundCenter.getImage();
        backgroundBorder = new ImageIcon(getClass().getClassLoader().getResource(pathBorder));
        backBorder = backgroundBorder.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
        debug(button, g);

        drawEnvironment();
        drawAnimal();
    }


    /**
     * Draw dashboard's animal
     */
    public void drawAnimal() { //Todo animations
        Image image = animal.getImage();

        int x = animal.getX();
        int y = animal.getY();
        g.drawImage(image, x * (this.getWidth() / 4), y * (this.getHeight() / 4),
                this.getWidth() / 4, this.getHeight() / 4, null);
    }

    /**
     * Draw dashboard's environment
     */
    public void drawEnvironment() {
        Image image;
        Cell[][] map = GameEngine.getInstance().getEnvironmentManager().
                getCells(placePosition, subplacePosition);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].isPee) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * (this.getWidth() / 4), j * (this.getHeight() / 4),
                            this.getWidth() / 5, this.getHeight() / 5);
                }
                if (map[i][j].getType() != "Empty") {
                    image = null;
                    switch (map[i][j].getType()) {
                        case "Ball":
                            image = new Ball(i, j).getImage();
                            break;
                        case "Table":
                            image = new Table(i, j).getImage();
                            break;
                        case "SubplaceTopDoor":
                            image = new SubplaceTopDoor(i, j).getImage();
                            break;
                        case "SubplaceDownDoor":
                            image = new SubplaceDownDoor(i, j).getImage();
                            break;
                        case "PlaceLeftDoor":
                            image = new PlaceLeftDoor(i, j).getImage();
                            break;
                        case "PlaceRightDoor":
                            image = new PlaceRightDoor(i, j).getImage();
                            break;
                    }
                    g.drawImage(image, i * (this.getWidth() / 4), j * (this.getHeight() / 4),
                            this.getWidth() / 4, this.getHeight() / 4, null);
                }
            }
        }
    }

    /**
     * Debug's element
     * Draw lines
     * @param g
     * @param xPlace x coordonate
     * @param yPlace y coordonate
     */
    private void crossDraw(Graphics g, int xPlace, int yPlace) {
        g.drawLine(xPlace - this.getWidth() / 12, yPlace, xPlace + this.getWidth() / 12, yPlace);
        g.drawLine(xPlace, yPlace - this.getHeight() / 12, xPlace, yPlace + this.getHeight() / 12);
    }

    /**
     * Debug's element
     * Draw a "wall"
     * @param g
     * @param xPlace      x coordonate
     * @param yPlace      y coordonate
     * @param orientation Wall's orientation
     */
    private void wallDraw(Graphics g, int xPlace, int yPlace, String orientation) {
        if (orientation.equals("horizontal"))
            g.drawLine(xPlace, yPlace, xPlace + this.getWidth() / 4, yPlace);
        else if (orientation.equals("vertical"))
            g.drawLine(xPlace, yPlace, xPlace, yPlace + this.getHeight() / 4);
    }

    /**
     * Debug's element
     * Draw a colored point
     * @param g
     * @param xPlace x coordonate
     * @param yPlace y coordonate
     * @param color  Point's color
     */
    private void colorPointDraw(Graphics g, int xPlace, int yPlace, String color) {
        if (color.equals("green"))
            g.setColor(Color.GREEN);
        else if (color.equals("blue"))
            g.setColor(Color.BLUE);
        else if (color.equals("red"))
            g.setColor(Color.RED);
        g.fillOval(xPlace * (this.getWidth() / 4), yPlace * (this.getHeight() / 4),
                this.getWidth() / 5, this.getHeight() / 5);
    }

    /**
     * Allows developper to debug dashboard
     *
     * @param b If true, creates debug
     * @param g
     */
    private void debug(boolean b, Graphics g) {
        if (b) {
            g.setColor(Color.BLUE);

            for (int k = 1; k < 4; k++)
                for (int l = 1; l < 4; l++) {
                    crossDraw(g, this.getWidth() / 4 * k, this.getHeight() / 4 * l);
                }
            wallDraw(g, this.getWidth() / 4, this.getHeight() / 4, "horizontal");
            wallDraw(g, this.getWidth() / 4, this.getHeight() / 4, "vertical");
            wallDraw(g, this.getWidth() / 2, this.getHeight() / 4, "vertical");
        }
    }

    public void setPlacePosition(String placePosition) {
        this.placePosition = placePosition;
    }

    public void setSubplacePosition(String subplacePosition) {
        this.subplacePosition = subplacePosition;
    }

    public void setState(int state) {
        this.state = state;
    }
}
