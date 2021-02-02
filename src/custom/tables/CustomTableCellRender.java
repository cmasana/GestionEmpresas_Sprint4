package custom.tables;

import custom.other.ColorsPalette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Allow customize the cells of a table
 * @author cmasana
 */
public class CustomTableCellRender extends DefaultTableCellRenderer {
    // Custom colors
    private final ColorsPalette DYE = new ColorsPalette();

    // Renderize the component with their configurations
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Cells configuration (UI)
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setFont(new Font("Open Sans", 0, 12));

        // Hover effect when a row is selected
        if (isSelected) {
            this.setBackground(DYE.getHOVER());
            this.setForeground(DYE.getTEXTHOVER());
        }
        else {
            this.setBackground(DYE.getCONTENT());
            this.setForeground(DYE.getTEXTNORMAL());
        }

        return this;
    }
}
