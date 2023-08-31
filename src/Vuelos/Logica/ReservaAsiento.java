package Vuelos.Logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ReservaAsiento {

    private String fecha;
    private String fecha_vuelo;
    private double costo;

    private CarritoAsientos reservas;

    public ReservaAsiento() {
        reservas = new CarritoAsientos();

    }


    public void reservar(Asiento a) {
        reservas.añadir(a);
    }

    public void cancelarReserva(Asiento a) {
        reservas.eliminar(a);
    }

    public void crearReserva(String fecha){
        this.costo = calcularCostoTotal();
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

    public double calcularCostoTotal() {
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
    public void pagar() {
    }
}


