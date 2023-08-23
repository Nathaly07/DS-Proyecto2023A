package Vuelos.Logica;


import Vuelos.DAO.AsientoDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private int cod_vuelo, capacidad;
    private String origen, destino, hora_salida, hora_llegada;
    private String fecha;
    private int duracion;
    private boolean estaDisponible;
    private List<Asiento> asientos;

    public Vuelo(int cod_vuelo, String origen, String destino, String hora_salida, String hora_llegada, String fecha, int duracion, int estaDisponible) {
        this.cod_vuelo = cod_vuelo;
        this.origen = origen;
        this.destino = destino;
        this.hora_salida = hora_salida;
        this.hora_llegada = hora_llegada;
        this.fecha = fecha;
        this.duracion = duracion;
        if (estaDisponible == 0) {

            this.estaDisponible = true;
        } else {

            this.estaDisponible = false;
        }
        this.asientos = null;
        setAsientos();
    }

    public Vuelo(int cod_vuelo,List<Asiento> asientos, String origen, String destino, String hora_salida, String hora_llegada, String fecha, int duracion, int estaDisponible) {
        this.cod_vuelo = cod_vuelo;
        this.asientos = asientos;
        this.origen = origen;
        this.destino = destino;
        this.hora_salida = hora_salida;
        this.hora_llegada = hora_llegada;
        this.fecha = fecha;
        this.duracion = duracion;
        if (estaDisponible == 0) {

            this.estaDisponible = true;
        } else {

            this.estaDisponible = false;
        }
    }

    public void setAsientos() {
        AsientoDAO asientos = new AsientoDAO();
        this.asientos = asientos.BuscarAsientos(this.cod_vuelo);
    }


    public void consultarAsientosDisponibles() {
        AsientoDAO asientoDAO = new AsientoDAO();
        List<Asiento> asientosDis = new ArrayList<>();
        for (Asiento a : this.asientos) {
            if (a.isEstaReservado()) {
                System.out.println(a.toString());
            }
        }
    }


    public void actualizarInformacion() {

    }

    public int getCod_vuelo() {
        return cod_vuelo;
    }

    public boolean isEstaDisponible() {
        return estaDisponible;
    }

    public Asiento seleccionarAsiento(int num) {
        for (Asiento a : this.asientos) {
            if (a.getNumero() == num) {
                return a;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Vuelo{ ccodigo vuelo: "+ this.cod_vuelo + " origen=" + origen + ", destino=" + destino + ", hora_salida=" + hora_salida + ", hora_llegada=" + hora_llegada + ", fecha=" + fecha + ", duracion=" + duracion + ", estaDisponible=" + estaDisponible + '}';
    }

    public void mostrarVuelo() {
        String cadena = toString() + "\n";
        for (Asiento a : asientos) {
            cadena += a.toString() + "\n";
        }
        System.out.println(cadena);
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}

