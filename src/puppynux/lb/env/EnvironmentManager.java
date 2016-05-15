package puppynux.lb.env;

import puppynux.lb.env.objects.Cell;
import puppynux.lb.env.objects.*;
import puppynux.lb.env.place.Place;
import puppynux.lb.env.subplaces.Subplace;
import puppynux.rg.AI.actions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * Created by loic on 06/03/16.
 */
public class EnvironmentManager implements Serializable {
    private ArrayList<Action> actionList;
    private EnvironmentData environmentData;

    public EnvironmentManager() {
        actionList = new ArrayList<>(Arrays.asList(
                new MoveUp(), new MoveDown(), new MoveLeft(), new MoveRight(), new Stay(), new Pee()));
        environmentData = new EnvironmentData();
    }

    public void createEnvironment(String path) {
        loadEnvironment(path);
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

    public Cell getCell(String place, String subplace, int x, int y) {
        return environmentData.getEnvironment().get(place).getSubplaces().get(subplace).getCell(x, y);
    }

    public Cell[][] getCells(String place, String subplace) {
        return environmentData.getSubplace(place, subplace).getCells();
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public RMatrix initRMatrix(Cell[][] cells) {
        RMatrix matrix = new RMatrix();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                pee(matrix.getActionList(), x, y);
                int position = x + 4 * y;
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
                        playBall(matrix, matrix.getActionList(), x, y, position);
                        if (x - 1 > -1) {
                            playBall(matrix, matrix.getActionList(), x - 1, y, position);
                        }
                        if (x + 1 < 4) {
                            playBall(matrix, matrix.getActionList(), x + 1, y, position);
                        }
                        if (y - 1 > -1) {
                            playBall(matrix, matrix.getActionList(), x, y - 1, position);
                        }
                        if (y + 1 < 4) {
                            playBall(matrix, matrix.getActionList(), x, y + 1, position);
                        }
                        break;
                    case "Table":
                        climbTable(matrix, matrix.getActionList(), x, y, position);
                        if (x - 1 > -1) {
                            climbTable(matrix, matrix.getActionList(), x - 1, y, position);
                        }
                        if (x + 1 < 4) {
                            climbTable(matrix, matrix.getActionList(), x + 1, y, position);
                        }
                        if (y - 1 > -1) {
                            climbTable(matrix, matrix.getActionList(), x, y - 1, position);
                        }
                        if (y + 1 < 4) {
                            climbTable(matrix, matrix.getActionList(), x, y + 1, position);
                        }
                        break;
                    case "SubplaceTopDoor":
                        changeSubplace(matrix,matrix.getActionList(), x, y, cells[x][y],
                                ((SubplaceDoor) cells[x][y]).getX() + ((SubplaceDoor) cells[x][y]).getY() * 4);
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
                    case "SubplaceDownDoor":
                        changeSubplace(matrix, matrix.getActionList(), x, y, cells[x][y],
                                ((SubplaceDoor) cells[x][y]).getX() + ((SubplaceDoor) cells[x][y]).getY() * 4);
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
                    case "PlaceLeftDoor":
                        changePlace(matrix, matrix.getActionList(), x, y, cells[x][y],
                                ((PlaceDoor) cells[x][y]).getX() + ((PlaceDoor) cells[x][y]).getY() * 4);
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
                    case "PlaceRightDoor":
                        changePlace(matrix, matrix.getActionList(), x, y, cells[x][y],
                                ((PlaceDoor) cells[x][y]).getX() + ((PlaceDoor) cells[x][y]).getY() * 4);
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
                }
            }

        }
        return matrix;
    }

    public void changePlace(RMatrix matrix, ArrayList<HashMap<Action, Boolean>> list, int x, int y, Cell cell, int destination) {
        ChangePlace changePlace = new ChangePlace();
        if (!actionList.contains(changePlace)) {
            actionList.add(changePlace);
        }
        changePlace.setPosition(destination);
        changePlace.setPlace(((PlaceDoor) cell).getPlace());
        changePlace.setSubplace(((PlaceDoor) cell).getSubplace());
        list.get(x + 4 * y).put(changePlace, true);
        if (!matrix.getPossibleActions().contains(changePlace)) {
            matrix.addPossibleAction(changePlace);
        }
    }

    public void changeSubplace(RMatrix matrix, ArrayList<HashMap<Action, Boolean>> list, int x, int y, Cell cell, int destination) {
        ChangeSubplace changeSubplace = new ChangeSubplace();
        if (!actionList.contains(changeSubplace)) {
            actionList.add(changeSubplace);
        }
        changeSubplace.setPosition(destination);
        changeSubplace.setSubplace(((SubplaceDoor) cell).getSubplace());
        list.get(x + 4 * y).put(changeSubplace, true);
        if (!matrix.getPossibleActions().contains(changeSubplace)) {
            matrix.addPossibleAction(changeSubplace);
        }
    }

    public void playBall(RMatrix matrix, ArrayList<HashMap<Action, Boolean>> list, int x, int y, int position) {
        PlayBall play = new PlayBall();
        if (!actionList.contains(play)) {
            actionList.add(play);
        }
        play.setPosition(position);
        list.get(x + 4 * y).put(play, true);
        if (!matrix.getPossibleActions().contains(play)) {
            matrix.addPossibleAction(play);
        }
    }

    public void climbTable(RMatrix matrix, ArrayList<HashMap<Action, Boolean>> list, int x, int y, int position) {
        ClimbTable climb = new ClimbTable();
        if (!actionList.contains(climb)) {
            actionList.add(climb);
        }
        climb.setPosition(position);
        list.get(x + 4 * y).put(climb, true);
        if (!matrix.getPossibleActions().contains(climb)) {
            matrix.addPossibleAction(climb);
        }
    }

    public void pee(ArrayList<HashMap<Action, Boolean>> list, int x, int y) {
        list.get(x + 4 * y).put(new Pee(), true);
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
