package puppynux.wr.gui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by niamor972 on 11/03/16.
 * Parts of puppynux.wr.gui.listeners.
 * >
 */
public class PuppyTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof PuppynuxButton) {
            return (PuppynuxButton) value;
        }
        else if (value instanceof JComboBox) {
            return (JComboBox) value;
        }
        else {
            return this;
        }
    }
}
