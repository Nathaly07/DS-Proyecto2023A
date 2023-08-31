package Vuelos.Logica;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class GestorVuelos {

    private List<Vuelo> vuelos = new ArrayList<>();

    public GestorVuelos() {
        Vuelo v1 = new Vuelo("New York", "Los Angeles", "08:00", "2023-08-25", 180);
        Vuelo v2 = new Vuelo("Miami", "Chicago", "08:00", "2023-08-25", 180);
        Vuelo v3 = new Vuelo("London", "Paris", "08:00", "2023-08-25", 180);
        Vuelo v4 = new Vuelo("Tokyo", "Sydney", "08:00", "2023-08-25", 180);
        Vuelo v5 = new Vuelo("San Francisco", "Seattle", "08:00", "2023-08-25", 180);
        Vuelo v6 = new Vuelo("New York", "Los Angeles", "08:00", "2023-08-25", 180);
        vuelos.add(v1);
        vuelos.add(v2);
        vuelos.add(v3);
        vuelos.add(v4);
        vuelos.add(v5);
        vuelos.add(v6);
    }

    public void mostrarVuelos(JTable tabla){
        TablaVuelos modelo = new TablaVuelos(this.vuelos);
        tabla.setModel(modelo);
    }
    public List<Vuelo> buscarVuelo(String origen, String destino) {
        List<Vuelo> vuelosEncontrados = new ArrayList<>();

        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getOrigen().equalsIgnoreCase(origen) && vuelo.getDestino().equalsIgnoreCase(destino)) {
                vuelosEncontrados.add(vuelo);
            }
        }
        return vuelosEncontrados;
    }

    public List<Vuelo> buscarVueloFecha(String fecha) {
        List<Vuelo> vuelosEncontrados = new ArrayList<>();

        for (Vuelo vuelo : this.vuelos) {
            if (fecha.equalsIgnoreCase(vuelo.getFecha())) {
                vuelosEncontrados.add(vuelo);
            }
        }
        return vuelosEncontrados;
    }

    public List<Vuelo> filtar(String origen, String destino, String fecha) {
        List<Vuelo> vuelosEncontrados = new ArrayList<>();

        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getOrigen().equalsIgnoreCase(origen) && vuelo.getDestino().equalsIgnoreCase(destino) && vuelo.getFecha().equalsIgnoreCase(fecha)) {
                vuelosEncontrados.add(vuelo);
            }
        }
        return vuelosEncontrados;
    }

    public void mostarVuelosFiltrados(JTable tabla, List<Vuelo> vuelos){
        TablaVuelos modelo = new TablaVuelos(vuelos);
        tabla.setModel(modelo);

    }
    public void agregarVuelo(Vuelo v) {
        this.vuelos.add(v);
    }

    private static class TablaVuelos extends AbstractTableModel {
    private final String[] COLUMNS = {"Origen","Destino", "Fecha", "Hora salida",
        "Duracion", "Numero de asientos"};
    private List<Vuelo> vuelos;

    public TablaVuelos(List<Vuelo> vuelos){
        this.vuelos = vuelos;
    }
        @Override
        public int getRowCount() {
            return vuelos.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch(columnIndex) {
                case 0 -> vuelos.get(rowIndex).getOrigen();
                case 1 -> vuelos.get(rowIndex).getDestino();
                case 2 -> vuelos.get(rowIndex).getFecha();
                case 3 -> vuelos.get(rowIndex).getHora_salida();
                case 4 -> vuelos.get(rowIndex).getDuracion();
                case 5 -> vuelos.get(rowIndex).getNumeroAsientos();
                default ->  "-";

            };
        }
        @Override
        public boolean isCellEditable(int row,int column){
            return false;
        }
        @Override
        public String getColumnName(int column){
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex){
        if(getValueAt(0,columnIndex) != null){
            return getValueAt(0,columnIndex).getClass();
        }else{
            return Object.class;
        }
        }

    }
    public Vuelo seleccionarVuelo(Vuelo v) {
        ComparadorVuelo com = new ComparadorVuelo();
        for(Vuelo aux : this.vuelos){
            if(com.compare(v,aux) == 1){
                return aux;
            }
        }
        return null;
    }

}