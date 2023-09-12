package Vuelos.Logica;


import Principal.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservaAsiento {

    private String fecha;
    private String fecha_vuelo;
    private double costo;
    private Usuario usuario;
    private EstadoReserva estado;

    private CarritoAsientos reservas;

    public ReservaAsiento(CarritoAsientos reservas, Usuario usuario) {
        this.reservas = reservas;
        this.fecha_vuelo = reservas.getVuelo().getFecha();
        this.estado = EstadoReserva.Pendiente;
        this.usuario = usuario;
    }

    private void reservarAsientos() {
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
        reservarAsientos();
        this.fecha = fechaAutomatica();
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




