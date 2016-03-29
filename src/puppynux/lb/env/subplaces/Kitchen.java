package puppynux.lb.env.subplaces;

import puppynux.lb.env.objects.Cell;
import puppynux.lb.env.objects.Empty;


public class Kitchen implements Subplace {
    private Cell[][] cells = new Cell[4][4];
    private int state;

    public Kitchen(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = new Empty();
            }
        }
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCells(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    @Override
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String getName() {
        return "Kitchen";
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Kitchen: \n");
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                buffer.append(cells[x][y].toString());
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

}