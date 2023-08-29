package Vuelos.Logica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {

    private String origen, destino, hora_salida;
    private String fecha;
    private int duracion;
    private boolean estaDisponible;
    private List<Asiento> asientos;
    private int numeroAsientos = 60;

    public Vuelo(String origen, String destino, String hora_salida, String fecha, int duracion, int estaDisponible, int numeroAsientos) {
        this.origen = origen;
        this.destino = destino;
        this.hora_salida = hora_salida;
        this.fecha = fecha;
        this.duracion = duracion;
        this.estaDisponible = true;
        this.numeroAsientos = numeroAsientos;
        this.asientos = new ArrayList<>();
        GenerarAsientos(duracion);
    }

    private void GenerarAsientos(int duracion){
        int numeroAsientoPorFila = 6;
        int cantidadFilaPremium = 4;
        int cantidadFilaTurista = 10;

        for(int numeroFilaPremium = 1; numeroFilaPremium <= cantidadFilaPremium; numeroFilaPremium++){
            crearAsiento(numeroAsientoPorFila, (int) duracion, 100, "Clase Premium", numeroFilaPremium);
        }

        for(int numeroFilaTurista = 5; numeroFilaTurista <= cantidadFilaTurista; numeroFilaTurista++){
            crearAsiento(numeroAsientoPorFila, (int) duracion, 75, "Clase Turista", numeroFilaTurista);
        }
    }

    private void crearAsiento(int numeroAsientoPorFila, int duracion, int precio, String Clase_Turista, int numeroFilaTurista) {
        for (int numeroAsiento = 1; numeroAsiento <= numeroAsientoPorFila; numeroAsiento++) {
            Asiento a;
            if (numeroAsiento % 3 == 0) {
                a = new Asiento(numeroAsiento, true, ((duracion / 60) * precio), Clase_Turista, numeroFilaTurista);
                this.asientos.add(a);
                continue;
            }
            a = new Asiento(numeroAsiento, false, ((duracion / 60) * precio), Clase_Turista, numeroFilaTurista);
            this.asientos.add(a);
        }
    }


    public void actualizarInformacion() {

    }


    public boolean getDisponibilidad() {
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
        return "Vuelo{ "+ " origen=" + origen + ", destino=" + destino + ", hora_salida=" + hora_salida +", fecha=" + fecha + ", duracion=" + duracion + ", estaDisponible=" + estaDisponible + '}';
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

    public String getHora_salida() {
        return hora_salida;
    }

    public String getFecha() {
        return fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }


}


