package Reservas;

import Vuelos.Logica.Asiento;
import Vuelos.Logica.CarritoAsientos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ReservaAsiento extends Reserva{

    private String fecha;
    private String fecha_vuelo;
    private double costo;

    private CarritoAsientos reservas;

    public ReservaAsiento(String IDReserva, String IDUsuario) {
        super(IDUsuario,IDReserva);
        reservas = new CarritoAsientos();

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

    public void imprimirDetalle() {
        String cadena = "Fecha Reserva: " + fecha +"\nfecha de vuelo: " + fecha_vuelo+
                "\n costo: " + costo + "\nAsientos Reservados:\n";
        for (Asiento a : reservas.getAsientos()){
            cadena += a.toString();
        }
        System.out.println(cadena);
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
}


