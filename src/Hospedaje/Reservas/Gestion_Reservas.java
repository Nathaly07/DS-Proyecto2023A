package Hospedaje.Reservas;

import Hospedaje.Criteria.CriteriaDisponibilidad;
import Hospedaje.Criteria.HospedajeCriteria;
import Hospedaje.Habitaciones.Gestion_Habitaciones;
import Hospedaje.Habitaciones.Habitacion;
import Reservas.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion_Reservas {
    private static List<ReservaHospedaje> reservas;

    public Gestion_Reservas() {
        this.reservas = new ArrayList<>();
        Date fechaCreacion = new Date();
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        Habitacion habitacion1 = Gestion_Habitaciones.getHabitaciones().get(1);
        Habitacion habitacion2 = Gestion_Habitaciones.getHabitaciones().get(2);
        Habitacion[] habitaciones = {habitacion1, habitacion2};

        this.reservas.add(new ReservaHospedaje("1" , "1", 2, habitaciones, fechaCreacion, fechaInicio, fechaFin));
    }

    public static List<Habitacion> getHabitacionesDisponibles(Date reservarDesde, Date reservarHasta) {
        List<Habitacion> habitaciones = Gestion_Habitaciones.getHabitaciones();
        HospedajeCriteria criteriaDisponibles = new CriteriaDisponibilidad(reservarDesde, reservarHasta);
        return criteriaDisponibles.meetCriteria(habitaciones, reservas);
    }
    public List<ReservaHospedaje> getReservas() {
        return reservas;
    }

    public void addReserva(ReservaHospedaje reserva) {
        this.reservas.add(reserva);
    }

    public boolean cancelarReserva(String reservaId) {
        for (ReservaHospedaje reserva : reservas) {
            if (reserva.getReservaId().equals(reservaId)) {
                this.reservas.remove(reserva);
                return true; // Reserva cancelada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }

    public boolean actualizarReserva(String reservaId, ReservaHospedaje nuevaReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getReservaId().equals(reservaId)) {
                this.reservas.set(i, nuevaReserva);
                return true; // Reserva actualizada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }
}
