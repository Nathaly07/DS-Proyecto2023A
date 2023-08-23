package Vuelos.Logica;

import Vuelos.DAO.VueloDAO;
import  Vuelos.Logica.ReservaAsiento;
import  Vuelos.Logica.Vuelo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoVuelos {

    private List<Vuelo> vuelos;
    private List<ReservaAsiento> reservas;

    /*public CatalogoVuelos() {
        actualizarCatalogo();
    }
     */

    public CatalogoVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    /*
    public void buscarVuelo(String origen, String destino) {
        VueloDAO v = new VueloDAO();
        List<Vuelo> vuelos_bus = v.ConsultarVuelo(origen, destino);
        for (Vuelo i: vuelos_bus){
            System.out.println(i.toString());
        }
    }
*/

    public void buscarVuelo(String origen, String destino) {
        List<Vuelo> vuelosEncontrados = new ArrayList<>();

        // Recorremos la lista de vuelos
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getOrigen().equalsIgnoreCase(origen) && vuelo.getDestino().equalsIgnoreCase(destino)) {
                // Si el origen y destino coinciden, añadimos el vuelo a la lista de vuelos encontrados
                vuelosEncontrados.add(vuelo);
            }
        }

        // Mostramos los vuelos encontrados
        if (vuelosEncontrados.isEmpty()) {
            System.out.println("No se encontraron vuelos con origen \"" + origen + "\" y destino \"" + destino + "\".");
        } else {
            System.out.println("Vuelos encontrados con origen \"" + origen + "\" y destino \"" + destino + "\":");
            for (Vuelo vuelo : vuelosEncontrados) {
                System.out.println(vuelo);
            }
        }
    }

    public void agregarVuelo(Vuelo v) {
        this.vuelos.add(v);
    }

    public Vuelo seleccionarVuelo(int cod_vuelo) {
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getCod_vuelo() == cod_vuelo) {
                return vuelo;
            }
        }
        return null;
    }



    /*public void actualizarCatalogo() {
        VueloDAO v = new VueloDAO();
        this.vuelos = v.ConsultarVuelo("","");
    }
     */
}

