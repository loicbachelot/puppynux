package puppynux.wr.gui.components;

import puppynux.wr.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by william on 17/03/16.
 * Contains MainWindow's background's elements
 */
public class BackgroundPanel extends JPanel {

    private int dim;
    private final ImageIcon backgroundCenter = new ImageIcon(getClass().getClassLoader().getResource("resources/img/roofCenter.png"));
    private Image backCenter = backgroundCenter.getImage();
    private final ImageIcon backgroundBorder = new ImageIcon(getClass().getClassLoader().getResource("resources/img/roofBorder.png"));
    private Image backBorder = backgroundBorder.getImage();

    private final ImageIcon gardenCenter = new ImageIcon(getClass().getClassLoader().getResource("resources/img/grass.png"));

    int optimizedSizeWidth;
    int optimizedSizeHeight;

    /**
     * @param dim Background's grid dimension
     */
    public BackgroundPanel(int dim) {
        this.dim = dim;
        Border border = new MatteBorder(0, 2, 0, 0, MainWindow.bordersColor);
        setBorder(border);
    }

    protected void paintComponent(Graphics g) {
        generateBackground(g);
    }

    /**
     * Generate the background of the game
     *
     * @param g
     */
    private void generateBackground(Graphics g) {
        optimizedSizeWidth = getWidth();
        optimizedSizeHeight = getHeight();
        g.setColor(MainWindow.bordersColor);
        g.fillRect(0,0,optimizedSizeWidth, optimizedSizeHeight);
        for (int j = 0; j < dim; j++)
            for (int i = 0; i < dim; i++) {
                if (i == 0 || j == 0 || i == dim - 1 || j == dim - 1) {
                    g.drawImage(backBorder, i * (optimizedSizeWidth / dim), j * (optimizedSizeHeight / dim),
                            optimizedSizeWidth / dim, optimizedSizeHeight / dim, null);
                } else g.drawImage(backCenter, i * (optimizedSizeWidth / dim), j * (optimizedSizeHeight / dim),
                        optimizedSizeWidth / dim, optimizedSizeHeight / dim, null);
            }
    }
}
