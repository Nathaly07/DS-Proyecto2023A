package Vuelos.Logica;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorVuelos {

    private List<Vuelo> vuelos = new ArrayList<Vuelo>();

    public GestorVuelos() {
        leerVuelosDesdeArchivo("src/Vuelos/Archivo/listaVuelos.txt");
    }

    public void leerVuelosDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String origen = partes[0];
                String destino = partes[1];
                String hora_salida = partes[2];
                String fecha = partes[3];
                int duracion = Integer.parseInt(partes[4]);
                agregarVuelo(new Vuelo(origen,destino,hora_salida,fecha,duracion));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public List<Vuelo> buscarVueloPorDestinoFecha(String destinoComun, String fechaComun) {
        List<Vuelo> vuelosEncontrados = new ArrayList<>();
        for (Vuelo vuelo : this.vuelos) {
            if (fechaComun.equalsIgnoreCase(vuelo.getFecha()) && vuelo.getDestino().equalsIgnoreCase(destinoComun)) {
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
        if(vuelos.size() > 0) {
            TablaVuelos modelo = new TablaVuelos(vuelos);
            tabla.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "No existen vuelos con esas caracteristicas", "Aviso", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void agregarVuelo(Vuelo v) {
        this.vuelos.add(v);
    }



    private static class TablaVuelos extends AbstractTableModel {
    private final String[] COLUMNS = {"Origen","Destino", "Fecha", "Hora salida",
        "Duracion", "Asientos Disponibles"};
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
                case 5 -> vuelos.get(rowIndex).asientosDisponibles();
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