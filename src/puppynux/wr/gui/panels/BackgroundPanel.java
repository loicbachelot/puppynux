package puppynux.wr.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 15/02/16.
 */

public class BackgroundPanel extends JPanel {

    private static final long serialVersionUID = -4061414677448186966L;
    protected int dim;
    private ImageIcon backgroundCenter = new ImageIcon(getClass().getClassLoader().getResource("resources/img/roofCenter.png"));
    private Image backCenter = backgroundCenter.getImage();
    private ImageIcon backgroundBorder = new ImageIcon(getClass().getClassLoader().getResource("resources/img/roofBorder.png"));
    private Image backBorder = backgroundBorder.getImage();

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
        int optimizedSizeWidth = this.getWidth();
        int optimizedSizeHeight = this.getHeight();
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
