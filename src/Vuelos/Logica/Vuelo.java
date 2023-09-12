package Vuelos.Logica;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {

    private  String origen, destino, horaSalida;
    private  String fecha;
    private  int duracion;
    private  int estaDisponible;
    private  List<Asiento> asientos;
    private  int numeroAsientos = 60;

    private int precioPremium = 150;
    private int precioTurista = 100;


    public Vuelo(String origen, String destino, String horaSalida, String fecha, int duracion) {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.fecha = fecha;
        this.duracion = duracion;
        this.estaDisponible = 1;
        this.asientos = new ArrayList<>();
        GenerarAsientos(duracion);
    }

    public Vuelo(String origen, String destino, String horaSalida, String fecha) {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
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
        for (int numeroAsiento = 1; numeroAsiento <= numeroAsientoPorFila; numeroAsiento++) {
            Asiento asiento;
            if (numeroAsiento  == 0) {
                asiento = new Asiento(numeroAsiento, true, ((duracion * precio)/ 60), Clase, numeroFilaTurista);
            } else {
                asiento = new Asiento(numeroAsiento, false, ((duracion * precio)/ 60), Clase, numeroFilaTurista);
            }
            this.asientos.add(asiento);
        }
    }


    public Asiento BuscarAsiento(Asiento asiento) {
        for (Asiento asientoSeleccionado : this.asientos) {
            if (asientoSeleccionado.getNumero() == asiento.getNumero() && asientoSeleccionado.getNumFila() == asiento.getNumFila()) {
                return asientoSeleccionado;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Vuelo{ "+ " origen=" + origen + ", destino=" + destino + ", hora_salida=" + horaSalida +", fecha=" + fecha + ", duracion=" + duracion + ", estaDisponible=" + estaDisponible + '}';
    }


    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public int asientosDisponibles(){
        int contador = 0;
        for(Asiento asiento: this.asientos){
            if(asiento.isEstaReservado() == false){
                contador++;
            }
        }
        return contador;
    }

    public List<Integer> getFila(int fila){
        List<Integer> lista = new ArrayList<>();
        for(Asiento asiento: this.asientos){
            if(asiento.isEstaReservado() == true && asiento.getNumFila() == fila){
                lista.add(asiento.getNumero());
            }
        }
        return lista;
    }
    public void actualizarListaAsiento(List<Asiento> seleccionados){
        for(Asiento asiento : this.asientos){
            for(Asiento asientoSeleccionado : seleccionados){
                if(asiento.getNumero() == asientoSeleccionado.getNumero() && asiento.getNumFila() == asientoSeleccionado.getNumFila()){
                    asiento.reservar();
                }
            }
        }

    }

}


