package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.ReservaHospedaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CriteriaDisponibilidadHabitaciones implements HospedajeCriteria<Habitacion, ReservaHospedaje> {
    private Date reservarDesde;
    private Date reservarHasta;

    public CriteriaDisponibilidadHabitaciones(Date reservarDesde, Date reservarHasta) {
        this.reservarDesde = reservarDesde;
        this.reservarHasta = reservarHasta;
    }

    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones, List<ReservaHospedaje> reservas) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>(habitaciones);

        for (ReservaHospedaje reserva : reservas) {
            Date fechaInicioReserva = reserva.getFechaInicio();
            Date fechaFinReserva = reserva.getFechaFin();

            // Si la reserva coincide con el rango de fechas solicitado, eliminamos todas las habitaciones de esa reserva de la lista de disponibles
            if (!(fechaInicioReserva.after(reservarHasta) || fechaFinReserva.before(reservarDesde))) {
                for (Habitacion habitacion : reserva.getHabitaciones()) {
                    habitacionesDisponibles.remove(habitacion);
                }
            }
        }

        return habitacionesDisponibles;
    }
}
