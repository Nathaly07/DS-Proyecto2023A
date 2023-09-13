package Vuelos.Logica;


import Principal.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservaAsiento {

    private String fecha;
    private String fechaVuelo;
    private double costo;
    private Usuario usuario;
    private EstadoReserva estado;

    private CarritoAsientos reservas;

    public ReservaAsiento(CarritoAsientos reservas, Usuario usuario) {
        this.reservas = reservas;
        this.fechaVuelo = reservas.getVuelo().getFecha();
        this.estado = EstadoReserva.Pendiente;
        this.usuario = usuario;
    }

    private void reservarAsientos() {
        for(Asiento asiento: reservas.getAsientos()){
            asiento.ocuparAsiento();
        }
    }
    public void crearReserva(){
        this.costo = generarCostoTotal();
        this.fechaVuelo = reservas.getVuelo().getFecha();
        reservarAsientos();
        this.fecha = fechaAutomatica();
    }
    public void cancelarReserva() {
        for(Asiento asiento: reservas.getAsientos()){
            asiento.desocuparAsiento();
        }
        this.cambiarEstado(EstadoReserva.Cancelado);
    }



    public double generarCostoTotal() {
        return generarCostoTotalPremium()+ generarCostoTotalTurista();
    }
    private String fechaAutomatica(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatoFecha);
        return fechaFormateada;
    }

    public int getCantidadAsientos() {
        return reservas.getAsientos().size();
    }

    public int obtenerCantidadAsientosReservadosPremium() {
        return obtenerCantidadAsientosPorTipo("Premium");
    }

    public int obtenerCantidadAsientosReservadosTurista() {
        return obtenerCantidadAsientosPorTipo("Turista");
    }

    private int obtenerCantidadAsientosPorTipo(String tipo) {
        int cantidad= 0;
        for (Asiento asiento : reservas.getAsientos()) {
            if (asiento.getTipo().equalsIgnoreCase(tipo)) {
                cantidad++;
            }
        }
        return cantidad;
    }


    public double generarCostoTotalPremium() {
        double total  = 0.0;
        for (Asiento asiento : reservas.getAsientos()){
            if (asiento.getTipo().equalsIgnoreCase("Premium")){
                total += asiento.getPrecio();
            }
        }
        return total;
    }

    public double generarCostoTotalTurista() {
        double total  = 0.0;
        for (Asiento asiento : reservas.getAsientos()){
            if (asiento.getTipo().equalsIgnoreCase("Turista")){
                total += asiento.getPrecio();
            }
        }
        return total;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public CarritoAsientos getReservas() {
        return  this.reservas;
    }

    public void cambiarEstado(EstadoReserva estado){
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }
}




