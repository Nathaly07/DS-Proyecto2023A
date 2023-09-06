package Hospedaje.Tests;

import Hospedaje.Habitaciones.GestionHabitaciones;
import Hospedaje.Reservas.GestionReservas;
import Hospedaje.Reservas.ReservaHospedaje;

import java.util.List;

public class ReservasTest {
    public static void main(String[] args) {
        GestionHabitaciones gestion_habitaciones = new GestionHabitaciones();
        GestionReservas gestion_reservas = new GestionReservas(gestion_habitaciones);

        // Reservas Totales
        List<ReservaHospedaje> reservas = gestion_reservas.getReservas();
        System.out.println("Habitaciones totales: " + reservas.size());
        reservas.forEach(reserva -> {
            System.out.println(reserva.getInfo());
        });
    }
}
