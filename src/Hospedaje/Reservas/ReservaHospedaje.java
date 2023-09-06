package Hospedaje.Reservas;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Pagos.PagoHospedaje;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaHospedaje {
    private int numeroPersonas;
    private Date fechaCreacion;
    private EstadoReserva estadoReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Habitacion habitaciones[];
    //TODO CAMBIAR Id's por login
    private String userId;
    private String reservaId;

    public String getUserId() {
        return userId;
    }

    public String getReservaId() {
        return reservaId;
    }

    public ReservaHospedaje(String userId, String reservaId, int numeroPersonas, Habitacion[] habitaciones, Date fechaCreacion, Date fechaInicio, Date fechaFin) {
        this.userId = userId;
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
}