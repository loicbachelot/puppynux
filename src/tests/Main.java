package tests;

import config.Config;
import org.json.simple.parser.ParseException;
import puppynux.gui.MainWindow;
import puppynux.rg.GameEngine;

import java.io.File;
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
            config.load(new File("src/config/config.json"));//config.getClass().getResource("config.json").getFile());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        GameEngine.getInstance().start();
        MainWindow mainWindow = new MainWindow();
        try {
            config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameEngine.getInstance().getAiManager().kill();
        GameEngine.getInstance().shutDown();
        System.out.println("Program ended correctly");
    }
}

