package puppynux.wr.gui.objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by william on 15/03/16.
 */
public abstract class Objects {
    private String image;
    private int x;
    private int y;
    private int oldX=0, oldY=0;

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

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }
}
