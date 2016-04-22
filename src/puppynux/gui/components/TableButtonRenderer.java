package puppynux.gui.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by niamor972 on 11/03/16.
 * Parts of puppynux.gui.components.
 * >
 */
public class TableButtonRenderer extends JButton implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        JButton button = (JButton) o;
        setText((o != null)? button.getText() : "");
        return this;
    }
}
