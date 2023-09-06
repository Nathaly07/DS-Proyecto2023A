package Hospedaje.Reservas;

import Hospedaje.Habitaciones.Habitacion;
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
    }

    public Usuario getUsuario() {
        return user;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }
}