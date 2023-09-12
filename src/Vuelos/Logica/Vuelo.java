package Vuelos.Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vuelo {

    private  String origen, destino, hora_salida;
    private  String fecha;
    private  int duracion;
    private  int estaDisponible;
    private  List<Asiento> asientos;
    private  int numeroAsientos = 60;

    private int precioPremium = 100;
    private int precioTurista = 75;


    public Vuelo(String origen, String destino, String hora_salida, String fecha, int duracion) {
        this.origen = origen;
        this.destino = destino;
        this.hora_salida = hora_salida;
        this.fecha = fecha;
        this.duracion = duracion;
        this.estaDisponible = 1;
        this.asientos = new ArrayList<>();
        GenerarAsientos(duracion);
    }

    public Vuelo(String origen, String destino, String hora_salida, String fecha) {
        this.origen = origen;
        this.destino = destino;
        this.hora_salida = hora_salida;
        this.fecha = fecha;
    }

    private void GenerarAsientos(int duracion){
        int numeroAsientoPorFila = 6;
        int cantidadFilaPremium = 4;
        int cantidadFilaTurista = 10;

        for(int numeroFilaPremium = 1; numeroFilaPremium <= cantidadFilaPremium; numeroFilaPremium++){
            crearAsiento(numeroAsientoPorFila, (int) duracion, precioPremium, "Premium", numeroFilaPremium);
        }

        for(int numeroFilaTurista = 5; numeroFilaTurista <= cantidadFilaTurista; numeroFilaTurista++){
            crearAsiento(numeroAsientoPorFila, (int) duracion, precioTurista, "Turista", numeroFilaTurista);
        }
    }

    private void crearAsiento(int numeroAsientoPorFila, int duracion, int precio, String Clase, int numeroFilaTurista) {
        Random random = new Random();
        int min = 1;
        int max = 6;
        for (int numeroAsiento = 1; numeroAsiento <= numeroAsientoPorFila; numeroAsiento++) {
            int numeroAsientoAleatorio2 = random.nextInt(max - min + 1) + min;
            Asiento a;
            if (numeroAsiento % numeroAsientoAleatorio2 == 0) {
                a = new Asiento(numeroAsiento, true, ((duracion / 60) * precio), Clase, numeroFilaTurista);

            } else {
                a = new Asiento(numeroAsiento, false, ((duracion / 60) * precio), Clase, numeroFilaTurista);
            }
            this.asientos.add(a);
        }
    }

    public  int getEstaDisponible() {
        return estaDisponible;
    }

    public Asiento seleccionarAsiento(Asiento asiento) {
        for (Asiento a : this.asientos) {
            if (a.getNumero() == asiento.getNumero() && a.getNumFila() == asiento.getNumFila()) {
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


    public int asientosDisponibles(){
        int contador = 0;
        for(Asiento a: this.asientos){
            if(a.isEstaReservado() == false){
                contador++;
            }
        }
        return contador;
    }

    public List<Integer> getFila(int fila){
        List<Integer> lista = new ArrayList<>();
        for(Asiento a: this.asientos){
            if(a.isEstaReservado() == true && a.getNumFila() == fila){
                lista.add(a.getNumero());
            }
        }
        return lista;
    }
    public void Actualizar(List<Asiento> seleccionados){
        for(Asiento a : this.asientos){
            for(Asiento aux : seleccionados){
                if(a.getNumero() == aux.getNumero() && a.getNumFila() == aux.getNumFila()){
                    a.reservar();
                }
            }
        }

    }

    public int getPrecioPremium() {
        return precioPremium;
    }

    public int getPrecioTurista() {
        return precioTurista;
    }
}


