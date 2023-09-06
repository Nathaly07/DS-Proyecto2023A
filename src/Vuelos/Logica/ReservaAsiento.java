package Vuelos.Logica;

import Reservas.Reserva;
import Vuelos.Logica.Asiento;
import Vuelos.Logica.CarritoAsientos;
import Vuelos.Logica.Vuelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservaAsiento {

    private String fecha;
    private String fecha_vuelo;
    private double costo;
    private EstadoReserva estado;

    private CarritoAsientos reservas;

    public ReservaAsiento(CarritoAsientos reservas) {
        this.reservas = reservas;
        this.fecha_vuelo = reservas.getVuelo().getFecha();
        this.estado = EstadoReserva.Pendiente;
    }

    public void reservar() {
        for(Asiento a: reservas.getAsientos()){
            a.reservar();
        }
    }

    public void cancelarReserva() {
        for(Asiento a: reservas.getAsientos()){
            a.CancelarReservaAsiento();
        }
        this.CambiarEstado(EstadoReserva.cancelado);
    }
    public void crearReserva(){
        this.costo = generarCostoTotal();
        this.fecha_vuelo = reservas.getVuelo().getFecha();
        this.fecha = fechaAutomatica();
    }

    public void ModificarReserva() {

    }


    public double generarCostoTotal() {
        return generarCostoTotalPremium()+ generarCostoTotalTurista();
    }
    private String fechaAutomatica(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatoFecha);
        return fechaFormateada;
    }

    public int getCantidadAsientos() {
        return reservas.getAsientos().size();
    }

    public int cantidadAsientosReservadosPremium() {
        return cantidadReserva("Premium");
    }

    public int cantidadAsientosReservadosTurista() {
        return cantidadReserva("Turista");
    }

    private int cantidadReserva(String tipo) {
        int cantidad= 0;
        for (Asiento a : reservas.getAsientos()) {
            if (a.getTipo().equalsIgnoreCase(tipo)) {
                cantidad++;
            }
        }
        return cantidad;
    }


    public double generarCostoTotalPremium() {
        double total  = 0.0;
        for (Asiento a : reservas.getAsientos()){
            if (a.getTipo().equalsIgnoreCase("Premium")){
                total += a.getPrecio();
            }
        }
        return total;
    }

    public double generarCostoTotalTurista() {
        double total  = 0.0;
        for (Asiento a : reservas.getAsientos()){
            if (a.getTipo().equalsIgnoreCase("Turista")){
                total += a.getPrecio();
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

    public void CambiarEstado(EstadoReserva estado){
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }
}




