package puppynux.lb.env.subplaces;

import puppynux.lb.env.objects.Cell;

import java.io.Serializable;

public interface Subplace extends Serializable {
    void setCells(int x, int y, Cell cell);
    Cell getCell(int x, int y);
    Cell[][] getCells();
    String getName();

}
