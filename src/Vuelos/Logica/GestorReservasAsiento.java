package Vuelos.Logica;

import Reservas.ReservaAsiento;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorReservasAsiento {
    private List<ReservaAsiento> reservas;

    public GestorReservasAsiento(){
        reservas = new ArrayList<>();
    }
    public void agregarResarva(ReservaAsiento r){
        reservas.add(r);
    }

public void quitarReserva(ReservaAsiento r){
    Iterator<ReservaAsiento> iterador = reservas.iterator();
    while (iterador.hasNext()) {
        ReservaAsiento i = iterador.next();
        if (i.equals(r)) {
            iterador.remove();
            break;
        }
    }
}

    public void mostrarReservas(JTable tabla) {
        GestorReservasAsiento.TablaReservas modelo = new TablaReservas(this.reservas);
        tabla.setModel(modelo);
    }

    private static class TablaReservas extends AbstractTableModel {
        private final String[] COLUMNS = {"Fecha", "Origen", "Destino", "Hora salida", "Num. Asientos"};
        private List<ReservaAsiento> reservas;
        public TablaReservas(List<ReservaAsiento> reservas){
            this.reservas = reservas;
        }

        @Override
        public int getRowCount() {
            return reservas.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> reservas.get(rowIndex).getReservas().getVuelo().getFecha();
                case 1 -> reservas.get(rowIndex).getReservas().getVuelo().getOrigen();
                case 2 -> reservas.get(rowIndex).getReservas().getVuelo().getDestino();
                case 3 -> reservas.get(rowIndex).getReservas().getVuelo().getHora_salida();
                case 4 -> reservas.get(rowIndex).getReservas().getVuelo().getNumeroAsientos();
                default -> "-";

            };
        }
        public String getColumnName(int column){
            return COLUMNS[column];
        }

        public Class<?> getColumnClass(int columnIndex){
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass();
            }else{
                return Object.class;
            }
        }
    }

}
