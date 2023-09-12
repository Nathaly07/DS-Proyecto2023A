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
    public void añadirReserva(ReservaAsiento reservaAsiento){
        reservas.add(reservaAsiento);
    }

    public int contarReservasPendientes(){
        int contador=0;
        for(ReservaAsiento reservaAsiento : this.reservas) {
           if(reservaAsiento.getEstado().equals(EstadoReserva.Pendiente)){
               contador++;
           }
        }
        return contador;
    }


    public void obtenerReservasPendientes(JTable tabla){
        List<ReservaAsiento> reservaAsientoSeleccionada  = new ArrayList<>();
        for(ReservaAsiento reservaAsiento : this.reservas){
            if(reservaAsiento.getEstado().equals(EstadoReserva.Pendiente)){
                reservaAsientoSeleccionada.add(reservaAsiento);
            }
        }
        GestorReservasAsiento.TablaReservas modelo = new TablaReservas(reservaAsientoSeleccionada);
        tabla.setModel(modelo);
    }

    public void mostrarReservas(JTable tabla) {
        GestorReservasAsiento.TablaReservas modelo = new TablaReservas(this.reservas);
        tabla.setModel(modelo);
    }

    public ReservaAsiento seleccionarReserva(ReservaAsiento reserva){
        ComparadorVuelo comparadorVuelo = new ComparadorVuelo();
        for(ReservaAsiento reservaAsientoSeleccionado : this.reservas){
            if(comparadorVuelo.compare(reservaAsientoSeleccionado.getReservas().getVuelo(), reserva.getReservas().getVuelo()) == 1 &&
            reserva.getEstado().equals(reservaAsientoSeleccionado.getEstado())){
                return reservaAsientoSeleccionado;
            }
        }
        return null;

    }

    public void eliminarReserva(ReservaAsiento reserva) {
        this.reservas.remove(reserva);

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
                case 3 -> reservas.get(rowIndex).getReservas().getVuelo().getHoraSalida();
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

    public boolean verificarReservasAsientos(){
        return reservas.size() > 0;
    }

}
