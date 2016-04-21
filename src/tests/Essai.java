package tests;

/**
 * Created by niamor972 on 22/03/16.
 * Parts of tests.
 * >
 */
public class Essai {

    public static void main (String [] args) {
        String test = null;
        try {
            test.equals("");
            System.out.println("test = " + test);
        } catch (NullPointerException e ) {
            System.err.println("exception\n");
        }
        System.out.println("test success\n");
    }
}
