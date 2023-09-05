package Reservas;

import Vuelos.Logica.Asiento;
import Vuelos.Logica.CarritoAsientos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ReservaAsiento extends Reserva{

    private String fecha;
    private String fecha_vuelo;
    private double costo;
    //private int numeroDeReservas;

    private CarritoAsientos reservas;

    public ReservaAsiento( String IDUsuario, String IDReserva, CarritoAsientos reservas) {
        super(IDUsuario,IDReserva);
        //String IDReserva = String.valueOf((numeroDeReservas++));
        this.reservas = reservas;
        this.fecha_vuelo = reservas.getVuelo().getFecha();

    }

    @Override
    public void cancelarReserva() {

    }

    @Override
    public void modificarReserva() {

    }

    public void reservar(Asiento a) {
        reservas.añadir(a);
    }

    public void cancelarReserva(Asiento a) {
        reservas.eliminar(a);
    }
    public void crearReserva(String fecha){
        this.costo = generarCostoTotal();
        this.fecha_vuelo = fecha;
        this.fecha = fechaAutomatica();
    }

    public void ModificarReserva() {

    }

    public String imprimirDetalle() {
        String cadena = "Fecha Reserva: " + fecha +"\nfecha de vuelo: " + fecha_vuelo+
                "\n costo: " + costo + "\nAsientos Reservados:\n";
        for (Asiento a : reservas.getAsientos()){
            cadena += a.toString();
        }
        return cadena;
    }

    public double generarCostoTotal() {
        double total  = 0.0;
        for (Asiento a : reservas.getAsientos()){
            total += a.getPrecio();
        }
        return total;
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
        int cantidad = 0;
        return cantidadReserva("Premium", cantidad);
    }

    public int cantidadAsientosReservadosTurista() {
        int cantidad = 0;
        return cantidadReserva("Turista", cantidad);
    }

    private int cantidadReserva(String tipo, int cantidad) {
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
    public CarritoAsientos getReservas() {
        return reservas;
    }
}




