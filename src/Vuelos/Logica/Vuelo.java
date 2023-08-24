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
    private int numeroAsientos;

    public Vuelo(String origen, String destino, String hora_salida, String fecha, int duracion, int estaDisponible, int numeroAsientos) {
        this.origen = origen;
        this.destino = destino;
        this.hora_salida = hora_salida;
        this.fecha = fecha;
        this.duracion = duracion;
        this.estaDisponible = true;
        this.numeroAsientos = numeroAsientos;
        this.asientos = new ArrayList<>();
        GenerarAsientos(numeroAsientos);
    }

    private void GenerarAsientos(int cantidad){
        int cantidadPremium = (int)(cantidad/4);
        for(int i = 1; i <= cantidadPremium; i++){
            Asiento a;
            a = new Asiento(i,200.0,"Clase Premium");
            this.asientos.add(a);
        }
        for(int i = cantidadPremium; i <= cantidad; i++){
            Asiento a;
            a = new Asiento(i,150.0,"Clase turista");
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


