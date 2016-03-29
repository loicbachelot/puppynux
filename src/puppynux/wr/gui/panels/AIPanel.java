package puppynux.wr.gui.panels;

import puppynux.wr.gui.objects.AI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 26/02/16.
 */

public class AIPanel extends BackgroundPanel {

    private AI ai;

    public AIPanel(AI ai, int dim) {
        super(dim);
        this.ai = ai;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        colorPointDraw(g, 1, 1, "green");
        colorPointDraw(g, 3, 3, "blue");
        g.drawImage(ai.getImage(), ai.getX() * (this.getWidth() / 4), ai.getY() * (this.getHeight() / 4),
                this.getWidth() / 4, this.getHeight() / 4, null);
        g.setColor(Color.BLUE);

        for (int k = 1; k < 4; k++)
            for (int l = 1; l < 4; l++) {
                crossDraw(g, this.getWidth() / 4 * k, this.getHeight() / 4 * l);
            }
        wallDraw(g, this.getWidth() / 4, this.getHeight() / 4, "horizontal");
        wallDraw(g, this.getWidth() / 4, this.getHeight() / 4, "vertical");
        wallDraw(g, this.getWidth() / 2, this.getHeight() / 4, "vertical");
    }

    private void crossDraw(Graphics g, int xPlace, int yPlace) {
        g.drawLine(xPlace - this.getWidth() / 12, yPlace, xPlace + this.getWidth() / 12, yPlace);
        g.drawLine(xPlace, yPlace - this.getHeight() / 12, xPlace, yPlace + this.getHeight() / 12);
    }

    private void wallDraw(Graphics g, int xPlace, int yPlace, String orientation) {
        if (orientation.equals("horizontal"))
            g.drawLine(xPlace, yPlace, xPlace + this.getWidth() / 4, yPlace);
        else if (orientation.equals("vertical"))
            g.drawLine(xPlace, yPlace, xPlace, yPlace + this.getHeight() / 4);
    }

    private void colorPointDraw(Graphics g, int xPlace, int yPlace, String color) {
        if (color.equals("green"))
            g.setColor(Color.GREEN);
        else if (color.equals("blue"))
            g.setColor(Color.BLUE);
        g.fillOval(xPlace * (this.getWidth() / 4), yPlace * (this.getHeight() / 4),
                this.getWidth() / 5, this.getHeight() / 5);
    }
}
