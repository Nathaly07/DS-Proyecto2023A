package Seguros.InterfacesGráficas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenderColores extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String estado = table.getValueAt(row, 5).toString();
        if(estado.equalsIgnoreCase("Activo")){
            setBackground(Color.GREEN);
            setForeground(Color.BLACK);
        } else if (estado.equalsIgnoreCase("Inactivo")) {
            setBackground(Color.RED);
            setForeground(Color.BLACK);
        } else if (estado.equalsIgnoreCase("Cobrado")) {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
