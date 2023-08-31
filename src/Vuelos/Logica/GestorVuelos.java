package Vuelos.Logica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class GestorVuelos {

    private List<Vuelo> vuelos = new ArrayList<>();
    private List<ReservaAsiento> reservas;


    public GestorVuelos() {
        Vuelo v1 = new Vuelo("New York", "Los Angeles", "08:00", "2023-08-25", 180, 0,200);
        Vuelo v2 = new Vuelo("Miami", "Chicago", "08:00", "2023-08-25", 180, 0,180);
        Vuelo v3 = new Vuelo("London", "Paris", "08:00", "2023-08-25", 180, 0,230);
        Vuelo v4 = new Vuelo("Tokyo", "Sydney", "08:00", "2023-08-25", 180, 0,130);
        Vuelo v5 = new Vuelo("San Francisco", "Seattle", "08:00", "2023-08-25", 180, 0,210);
        Vuelo v6 = new Vuelo("New York", "Los Angeles", "08:00", "2023-08-25", 180, 0,150);
        vuelos.add(v1);
        vuelos.add(v2);
        vuelos.add(v3);
        vuelos.add(v4);
        vuelos.add(v5);
        vuelos.add(v6);
    }

    public void mostrarVuelos(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Origen");
        modelo.addColumn("Destino");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora salida");
        modelo.addColumn("Duracion");
        modelo.addColumn("Numero de asientos");
        modelo.addColumn("Disponibilidad");
        tabla.setModel(modelo);
        Object[] ob = new Object[7];
        for(Vuelo v: this.vuelos){
            ob[0] = v.getOrigen();
            ob[1] = v.getDestino();
            ob[2] = v.getFecha();
            ob[3] = v.getHora_salida();
            ob[4] = v.getDuracion();
            ob[5] = v.getNumeroAsientos();
            ob[6] = v.getDisponibilidad();
            modelo.addRow(ob);
        }
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
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Origen");
        modelo.addColumn("Destino");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora salida");
        modelo.addColumn("Duracion");
        modelo.addColumn("Numero de asientos");
        modelo.addColumn("Disponibilidad");
        tabla.setModel(modelo);
        Object[] ob = new Object[7];
        for(Vuelo v: vuelos){
            ob[0] = v.getOrigen();
            ob[1] = v.getDestino();
            ob[2] = v.getFecha();
            ob[3] = v.getHora_salida();
            ob[4] = v.getDuracion();
            ob[5] = v.getNumeroAsientos();
            ob[6] = v.getDisponibilidad();
            modelo.addRow(ob);
        }
        tabla.setModel(modelo);

    }
    public void agregarVuelo(Vuelo v) {
        this.vuelos.add(v);
    }

}