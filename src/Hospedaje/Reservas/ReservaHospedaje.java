package Hospedaje.Reservas;

import Hospedaje.Habitaciones.Habitacion;
import Pagos.Pago;
import Principal.Usuario;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaHospedaje implements Serializable {
    private static final long serialVersionUID = 3L;
    private int numeroPersonas;
    private Date fechaCreacion;
    private EstadoReserva estadoReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Habitacion habitaciones[];
    //TODO CAMBIAR Id's por login
    private Usuario user;
    private String reservaId;
    private int dias;
    private double subtotal;
    private double iva;
    private double total;

    public Usuario getUser() {
        return user;
    }

    public String getReservaId() {
        return reservaId;
    }

    public ReservaHospedaje(Usuario user, String reservaId, int numeroPersonas, Habitacion[] habitaciones, Date fechaCreacion, Date fechaInicio, Date fechaFin) {
        this.user = user;
        this.reservaId = reservaId;
        this.numeroPersonas = numeroPersonas;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitaciones = habitaciones;
        this.estadoReserva = EstadoReserva.PENDIENTE;
    }

    //TODO IMPLEMENTAR SUS PROPIOS METODOS
    public void cancelarReserva() {
        this.estadoReserva = EstadoReserva.CANCELADA;
        this.generarPrecio();
    }


    public void modificarReserva() {

    }

    public String getInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder habitacionesInfo = new StringBuilder();
        habitacionesInfo.append("\nHabitaciones: ");
        for (Habitacion habitacion : habitaciones) {
            habitacionesInfo.append(habitacion.getInfo());
        }

        return "ReservaHospedaje:" +
                "\n\tnumeroPersonas=" + numeroPersonas +
                "\n\tfechaCreacion=" + dateFormat.format(fechaCreacion) +
                //"\nfechaCancelacionPago=" + dateFormat.format(fechaCancelacionPago) +
                "\n\tfechaInicio=" + dateFormat.format(fechaInicio) +
                "\n\tfechaFin=" + dateFormat.format(fechaFin) +
                "\n\tHabitaciones:" + habitacionesInfo.toString() +
                "\n";
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public void confirmarReserva() {
        this.estadoReserva = EstadoReserva.CONFIRMADA;
        Pago pago = new Pago(this.total, "Efectivo");
        pago.pagar();
    }

    public Usuario getUsuario() {
        return user;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void generarPrecio(){
        dias = (int) ((fechaFin.getTime() - fechaInicio.getTime()) / 86400000);
        subtotal = 0;
        for (Habitacion habitacion : habitaciones) {
            subtotal += habitacion.getPrecioPorNoche() * dias;
        }
        iva = subtotal * 0.12;
        total = subtotal + iva;
    }

    public double getSubtotal(){
        return subtotal;
    }

    public double getIVA(){
        return iva;
    }

    public double getTotal(){
        return total;
    }

    public int getDias(){
        return dias;
    }
}