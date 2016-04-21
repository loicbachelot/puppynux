package puppynux.gui.components;

import javax.swing.*;
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
    private Image grdCenter = gardenCenter.getImage();
    private Dimension size = getPreferredSize();

    /**
     * @param dim Background's grid dimension
     */
    public BackgroundPanel(int dim) {
        this.dim = dim;
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
        int optimizedSizeWidth = getWidth();
        int optimizedSizeHeight = getHeight();
        while (optimizedSizeWidth % dim != 0)
            optimizedSizeWidth--;
        while (optimizedSizeHeight % dim != 0)
            optimizedSizeHeight++;
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
