package Vuelos.Logica;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GestorReservasAsiento {
    private List<ReservaAsiento> reservas;

    public GestorReservasAsiento(){
        reservas = new ArrayList<>();
    }
    public void agregarResarva(ReservaAsiento r){
        reservas.add(r);
    }

    public int CantidadReservasPendientes(){
        int contador=0;
        for(ReservaAsiento r : this.reservas) {
           if(r.getEstado().equals(EstadoReserva.Pendiente)){
               contador++;
           }
        }
        return contador;
    }

    public void mostrarReservas(JTable tabla) {
        GestorReservasAsiento.TablaReservas modelo = new TablaReservas(this.reservas);
        tabla.setModel(modelo);
    }

    public void reservasPendientes(JTable tabla){
        List<ReservaAsiento> aux  = new ArrayList<>();
        for(ReservaAsiento r : this.reservas){
            if(r.getEstado().equals(EstadoReserva.Pendiente)){
                aux.add(r);
            }
        }
        GestorReservasAsiento.TablaReservas modelo = new TablaReservas(aux);
        tabla.setModel(modelo);
    }

    public ReservaAsiento SeleccionarReserva(ReservaAsiento reserva){
        ComparadorVuelo com = new ComparadorVuelo();
        for(ReservaAsiento aux : this.reservas){
            if(com.compare(aux.getReservas().getVuelo(), reserva.getReservas().getVuelo()) == 1 &&
            reserva.getEstado().equals(aux.getEstado())){
                return aux;
            }

        }
        return null;

    }

    private class TablaReservas extends AbstractTableModel {
        private final String[] COLUMNS = {"Fecha", "Origen", "Destino", "Hora salida","Fecha Vuelo", "Num. Asientos","Estado"};
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
                case 0 -> reservas.get(rowIndex).getFecha();
                case 1 -> reservas.get(rowIndex).getReservas().getVuelo().getOrigen();
                case 2 -> reservas.get(rowIndex).getReservas().getVuelo().getDestino();
                case 3 -> reservas.get(rowIndex).getReservas().getVuelo().getHora_salida();
                case 4 -> reservas.get(rowIndex).getReservas().getVuelo().getFecha();
                case 5 -> reservas.get(rowIndex).getReservas().getAsientos().size();
                case 6 -> reservas.get(rowIndex).getEstado();
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

    public boolean VerificarReservasAsientos(){
        return reservas.size() > 0;
    }

}
