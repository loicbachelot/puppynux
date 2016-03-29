package puppynux.lb.env.subplaces;

import puppynux.lb.env.objects.Cell;

public interface Room {
    public Object getObjects();
    public void setCells(int x, int y, Cell cell);
}
