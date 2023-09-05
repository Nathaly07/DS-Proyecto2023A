package Hospedaje.Tests;

import Hospedaje.Habitaciones.Gestion_Habitaciones;
import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.Gestion_Reservas;
import Hospedaje.Reservas.ReservaHospedaje;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestCriteriaDisponibilidad {
    public static void main(String[] args) {
        try {
            // Crear una instancia de Gestion_Habitaciones y Gestion_Reservas
            Gestion_Habitaciones gestionHabitaciones = new Gestion_Habitaciones();
            Gestion_Reservas gestionReservas = new Gestion_Reservas(gestionHabitaciones);

            // Definir las fechas para buscar habitaciones disponibles
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date reservarDesde = dateFormat.parse("2023-09-02");
            Date reservarHasta = dateFormat.parse("2023-09-03");

            // Crear reservas adicionales para ocupar 6 habitaciones
            for (int i = 3; i < 9; i++) {
                Habitacion habitacion = gestionHabitaciones.getHabitaciones().get(i);
                ReservaHospedaje reserva = new ReservaHospedaje("1", "1", 2, new Habitacion[]{habitacion}, new Date(), reservarDesde, reservarHasta);
                gestionReservas.crearReserva(reserva);
            }

            // Obtener las habitaciones disponibles
            List<Habitacion> habitacionesDisponibles = gestionReservas.getHabitacionesDisponibles(reservarDesde, reservarHasta);

            // Imprimir las habitaciones disponibles
            System.out.println("Habitaciones disponibles:");
            for (Habitacion habitacion : habitacionesDisponibles) {
                System.out.println(habitacion.getInfo());
            }

            // Verificar que hay 4 habitaciones disponibles
            if (habitacionesDisponibles.size() == 4) {
                System.out.println("Test exitoso: hay 4 habitaciones disponibles.");
            } else {
                System.out.println("Test fallido: se esperaban 4 habitaciones disponibles, pero se encontraron " + habitacionesDisponibles.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
