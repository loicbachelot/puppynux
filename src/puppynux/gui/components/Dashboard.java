package puppynux.gui.components;

import puppynux.gui.objects.Dog;
import puppynux.gui.objects.Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by william on 09/03/16.
 * Contains MainWindow's dashboard's elements
 */
public class Dashboard extends BackgroundPanel {

    Objects object;
    Graphics g;
    boolean button = false;
    public JButton debug = new JButton("Debug");

    /**
     *
     * @param object Animal initialized
     * @param dim Dashboard's grid dimension
     */
    public Dashboard(Objects object, int dim) {
        super(dim);
        this.object = object;
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
     *
     * @return Dashboard's animal
     */
    public Objects getObject() {
        return object;
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
        debug(button, g);

        colorPointDraw(g, 0, 1, "red");
        colorPointDraw(g, 3, 3, "blue");
        drawObjects();
    }

    /**
     * Draw dashboard's animal
     */
    public void drawObjects() {
        Image image = object.getImage();
        int x = object.getX();
        int y = object.getY();
       g.drawImage(image, x * (this.getWidth() / 4), y * (this.getHeight() / 4),
                this.getWidth() / 4, this.getHeight() / 4, null);
    }

    /**
     * Debug's element
     * Draw a cross
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
     * @param xPlace x coordonate
     * @param yPlace y coordonate
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
     * @param color Point's color
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
