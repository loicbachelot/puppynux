package puppynux.lb.env.subplaces;

import puppynux.lb.env.objects.Cell;

public interface Subplace {
    void setCells(int x, int y, Cell cell);
    Cell getCell(int x, int y);
    Cell[][] getCells();
    String getName();

}
