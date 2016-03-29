package puppynux.gui.objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 15/03/16.
 */
public abstract class Objects {
    private String image;
    private int x;
    private int y;

    public Objects(String image, int x, int y) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon(image);
        return imageIcon.getImage();
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
