package tests;

import config.Config;
import org.json.simple.parser.ParseException;
import puppynux.rg.GameEngine;
import puppynux.wr.gui.MainWindow;

import java.io.IOException;

/**
 * Created by Niamor972 on 16/02/16.
 * Parts of tests.
 * >
 **/

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Config config = Config.getInstance();

        try {
            config.load();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        GameEngine gameEngine = GameEngine.getInstance();
        gameEngine.start();
        MainWindow mainWindow = new MainWindow();
        try {
            config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameEngine.getAiManager() != null)
            gameEngine.getAiManager().kill();
        gameEngine.shutDown();
        System.out.println("Program ended correctly");
    }
}

