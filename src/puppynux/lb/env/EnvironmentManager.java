package puppynux.lb.env;

import puppynux.lb.env.objects.Cell;
import puppynux.lb.env.place.Place;
import puppynux.lb.env.subplaces.Subplace;
import puppynux.rg.AI.actions.*;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by loic on 06/03/16.
 */
public class EnvironmentManager {
    private EnvironmentData environmentData;

    public EnvironmentManager() {
        environmentData = new EnvironmentData();
    }

    public void createEnvironment () {
        loadEnvironment("env.xml");
        for (Place place :
                environmentData.getEnvironment().values()) {
            for (Subplace subplace :
                    place.getSubplaces().values()) {
                environmentData.addMatrixSubplace(subplace.getName(), place.getName(), initRMatrix(subplace.getCells()));

            }
        }
    }

    public void loadEnvironment(String path) {
        environmentData.load(path);
    }

    public Cell getCell (String place, String subplace, int x, int y) {
        return environmentData.getEnvironment().get(place).getSubplaces().get(subplace).getCell(x, y);
    }

    public Cell[][] getCells (String place, String subplace) {
        return environmentData.getSubplace(place, subplace).getCells();
    }

    public RMatrix initRMatrix(Cell[][] cells) {
        RMatrix matrix = new RMatrix();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                pee(matrix.getActionList(),x,y);
                switch (cells[x][y].getType()) {
                    case "Empty":
                        if (x - 1 > -1) {
                            moveRight(matrix.getActionList(), x - 1, y);
                        }
                        if (x + 1 < 4) {
                            moveLeft(matrix.getActionList(), x + 1, y);
                        }
                        if (y - 1 > -1) {
                            moveDown(matrix.getActionList(), x, y - 1);
                        }
                        if (y + 1 < 4) {
                            moveUp(matrix.getActionList(), x, y + 1);
                        }
                        stay(matrix.getActionList(), x, y);
                        break;
                    case "Ball":
                        playBall(matrix.getActionList(), x, y);
                        if (x - 1 > -1) {
                            playBall(matrix.getActionList(), x - 1, y);
                        }
                        if (x + 1 < 4) {
                            playBall(matrix.getActionList(), x + 1, y);
                        }
                        if (y - 1 > -1) {
                            playBall(matrix.getActionList(), x, y - 1);
                        }
                        if (y + 1 < 4) {
                            playBall(matrix.getActionList(), x, y + 1);
                        }
                        break;
                    case "Table":
                        climbTable(matrix.getActionList(), x, y);
                        if (x - 1 > -1) {
                            climbTable(matrix.getActionList(), x - 1, y);
                        }
                        if (x + 1 < 4) {
                            climbTable(matrix.getActionList(), x + 1, y);
                        }
                        if (y - 1 > -1) {
                            climbTable(matrix.getActionList(), x, y - 1);
                        }
                        if (y + 1 < 4) {
                            climbTable(matrix.getActionList(), x, y + 1);
                        }
                        break;
                }
            }

        }
        return matrix;
    }

    public void moveUp(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new MoveUp(), true);
    }

    public void moveDown(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new MoveDown(), true);
    }

    public void moveLeft(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new MoveLeft(), true);
    }

    public void moveRight(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new MoveRight(), true);
    }

    public void stay(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new Stay(), true);
    }

    public void playBall(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new PlayBall(), true);
    }

    public void climbTable(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new ClimbTable(), true);
    }

    public void pee(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new Pee(), true);
    }

//    public void door(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
//        list.get(x + 4 * y).put(new PassDoor(), true);
//    }

    public RMatrix getRMatrix(String placeString, String subplaceString) {
        return environmentData.getRMatrix(placeString, subplaceString);
    }

    public Subplace getSubplace(String placeString, String subplaceString) {
        return environmentData.getSubplace(placeString, subplaceString);
    }

    public HashMap<String, Place> getEnvironment() {
        return environmentData.getEnvironment();
    }

}
