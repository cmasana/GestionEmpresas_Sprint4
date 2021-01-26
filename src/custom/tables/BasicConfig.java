package custom.tables;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Proporciona la configuración estética de las tablas
 */
public class BasicConfig {
    public BasicConfig(JTable table) {
        initConfig(table);
    }

    public static void initConfig(JTable table) {
        // Asignamos un alto fijo para cada fila
        table.setRowHeight(30);
        // Asignamos un ancho fijo a las columnas con menos información
        table.getColumnModel().getColumn(0).setMaxWidth(250);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        table.setIntercellSpacing(new Dimension(1,1));

        // Permite personalizar el encabezado de la tabla
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new custom.tables.CustomTableHeaderRender());
        table.setTableHeader(tableHeader);

        // Permite personalizar las celdas de la tabla
        table.setDefaultRenderer(Object.class, new custom.tables.CustomTableCellRender());
    }
}
