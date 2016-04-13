package puppynux.gui.objects;


import javax.swing.*;
import javax.swing.text.StringContent;

/**
 * Created by william on 15/03/16.
 */
public class Dog extends Objects {
    private String backImage = "src/resources/img/dogBack.png";

    public Dog(int x, int y) {
        super("src/resources/img/dog.png", x, y);
    }
}
