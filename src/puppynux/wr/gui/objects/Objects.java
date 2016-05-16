package puppynux.wr.gui.objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 15/03/16.
 * Abstract class for every elements of the environment
 */
public abstract class Objects {
    private String image;
    private int x;
    private int y;

    /**
     * An object has a position (x and y) and an image
     *
     * @param image
     * @param x
     * @param y
     */
    public Objects(String image, int x, int y) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
    }

    /**
     * @return image of the object
     */
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(image));
        return imageIcon.getImage();
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
