package puppynux.gui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by niamor972 on 11/03/16.
 * Parts of puppynux.gui.listeners.
 * >
 */
public class PuppyTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            return (JButton) value;
        }
        else if (value instanceof JComboBox) {
            return (JComboBox) value;
        }
        else {
            return this;
        }
    }
}