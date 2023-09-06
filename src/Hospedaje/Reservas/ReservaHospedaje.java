package Hospedaje.Reservas;

import Hospedaje.Habitaciones.Habitacion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaHospedaje extends Reserva {
    private int numeroPersonas;
    private Date fechaCreacion;
    private EstadoReserva estadoReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Habitacion habitaciones[];
    public ReservaHospedaje(String userId, String reservaId, int numeroPersonas, Habitacion[] habitaciones, Date fechaCreacion, Date fechaInicio, Date fechaFin) {
        super(userId, reservaId);
        this.numeroPersonas = numeroPersonas;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitaciones = habitaciones;
        this.estadoReserva = EstadoReserva.PENDIENTE;
    }

    @Override
    public void cancelarReserva() {
        this.estadoReserva = EstadoReserva.CANCELADA;
    }

    @Override
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

    public String getReservaId() {
        return this.reservaID;
    }
    public void confirmarReserva() {
        this.estadoReserva = EstadoReserva.CONFIRMADA;
    }
}