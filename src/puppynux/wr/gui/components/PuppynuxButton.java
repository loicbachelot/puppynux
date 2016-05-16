package puppynux.wr.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by william on 21/04/16.
 * Custom buttons
 */
public class PuppynuxButton extends JButton {
    public PuppynuxButton(String title) {
        super(title);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);
    }

    public PuppynuxButton(ImageIcon image) {
        super(image);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed()) {
            g.setColor(g.getColor());
            g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
        }
        super.paintComponent(g);
        g2.setColor(new Color(30, 13, 128));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 3),
                (getHeight() - 3), 12, 8));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(4, getHeight() - 3, getWidth() - 4, getHeight() - 3);
        g2.dispose();
    }
}
