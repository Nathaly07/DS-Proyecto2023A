package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.ReservaHospedaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CriteriaDisponibilidadHabitaciones implements CriteriaDoble<Habitacion, ReservaHospedaje> {
    private Date reservarDesde;
    private Date reservarHasta;

    public CriteriaDisponibilidadHabitaciones(Date reservarDesde, Date reservarHasta) {
        this.reservarDesde = reservarDesde;
        this.reservarHasta = reservarHasta;
    }

    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones, List<ReservaHospedaje> reservas) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>(habitaciones);

        // Obtener reservas que concuerden con las fechas de reserva para posteriormente filtrar las
        // habitaciones que no esten reservadas
        List<ReservaHospedaje> reservasFiltradas = new CriteriaPorFechas(reservarDesde, reservarHasta).meetCriteria(reservas);

        // Eliminar habitaciones que concuerden con el id de las reservas filtradas
        for (ReservaHospedaje reserva : reservasFiltradas) {
            // Eliminar habitacion que ha sido reservada en el rango de fechas de la lista total de habitaciones
            habitacionesDisponibles.removeIf(habitacion -> habitacion.getHabitacionId() == (reserva.getHabitaciones()[0].getHabitacionId()));
        }

        return habitacionesDisponibles;
    }
}
