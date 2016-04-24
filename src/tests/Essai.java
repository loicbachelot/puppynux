package tests;

import puppynux.rg.AI.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by niamor972 on 22/03/16.
 * Parts of tests.
 * >
 */
public class Essai {

    public static void main (String [] args) {
        ArrayList<Action> actionList = new ArrayList<>(Arrays.asList(new MoveUp(), new MoveDown(), new MoveLeft(),
                new MoveRight(), new Stay(), new Pee()));

    }
}
