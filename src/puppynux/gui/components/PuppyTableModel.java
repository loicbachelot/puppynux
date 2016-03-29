package puppynux.gui.components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * Created by niamor972 on 11/03/16.
 * Parts of puppynux.gui.
 * >
 */
public class PuppyTableModel extends AbstractTableModel {

    private Object [][] data;
    private String [] titles;

    public PuppyTableModel(Object[][] data, String[] titles) {
        this.data = data;
        this.titles = titles;
    }

    @Override
    public Class getColumnClass (int columnIndex) {
        return data[0][columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName (int columnIndex) {
        return titles[columnIndex];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        return data[i][j];
    }
}
