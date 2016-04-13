package puppynux.gui.components;

import puppynux.gui.objects.Ball;
import puppynux.gui.objects.Objects;
import puppynux.gui.objects.Table;
import puppynux.lb.env.objects.Cell;
import puppynux.lb.env.objects.Empty;
import puppynux.rg.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 09/03/16.
 * Contains MainWindow's dashboard's elements
 */
public class Dashboard extends BackgroundPanel {

    Objects animal;
    Objects objects;
    Graphics g;
    int value = 0;
    boolean button = false;
    public JButton debug = new JButton("Debug");

    /**
     * @param animal Animal initialized
     * @param dim    Dashboard's grid dimension
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
    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
        debug(button, g);

        drawEnvironment();
//        colorPointDraw(g, 0, 1, "red");
//        colorPointDraw(g, 3, 3, "blue");
        drawAnimal();
    }


    /**
     * Draw dashboard's animal
     */

    public void drawAnimal() { //Todo animations
        Image image = animal.getImage();

        int x = animal.getX();
        int y = animal.getY();
//        if (animal.getOldX() - x == 0 && animal.getOldY() - y == 0) {
        g.drawImage(image, x * (this.getWidth() / 4), y * (this.getHeight() / 4),
                this.getWidth() / 4, this.getHeight() / 4, null);
//        } else if (animal.getOldX() - x == 0 && animal.getOldY() > y) {
//            g.drawImage(image, animal.getOldX() * (this.getWidth() / 4), animal.getOldY() * (this.getHeight() / 4) - value,
//                    this.getWidth() / 4, this.getHeight() / 4, null);
//        } else if (animal.getOldX() - x == 0 && animal.getOldY() < y) {
//            g.drawImage(image, animal.getOldX() * (this.getWidth() / 4), animal.getOldY() * (this.getHeight() / 4) + value,
//                    this.getWidth() / 4, this.getHeight() / 4, null);
//        } else if (animal.getOldY() - y == 0 && animal.getOldX() > x) {
//            g.drawImage(image, animal.getOldX() * (this.getWidth() / 4) - value, animal.getOldY() * (this.getHeight() / 4),
//                    this.getWidth() / 4, this.getHeight() / 4, null);
//        } else if (animal.getOldY() - y == 0 && animal.getOldX() < x) {
//            g.drawImage(image, animal.getOldX() * (this.getWidth() / 4) + value, animal.getOldY() * (this.getHeight() / 4),
//                    this.getWidth() / 4, this.getHeight() / 4, null);
//        }
    }

    public void drawEnvironment() {
        Image image;
        Cell[][] map = GameEngine.getInstance().getEnvironmentManager().
                getCells(GameEngine.getInstance().getAgentPlacePosition(),
                        GameEngine.getInstance().getAgentSubplacePosition());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].getType() != "Empty") {
                    switch (map[i][j].getType()) {
                        case "Ball":
                            image = new Ball(i, j).getImage();
                            g.drawImage(image, i * (this.getWidth() / 4), j * (this.getHeight() / 4),
                                    this.getWidth() / 4, this.getHeight() / 4, null);
                            break;
                        case "Table":
                            image = new Table(i, j).getImage();
                            g.drawImage(image, i * (this.getWidth() / 4), j * (this.getHeight() / 4),
                                    this.getWidth() / 4, this.getHeight() / 4, null);
                            break;
                    }
                }
            }

        }

    }

    /**
     * Debug's element
     * Draw a cross
     *
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
     *
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
     *
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
}
