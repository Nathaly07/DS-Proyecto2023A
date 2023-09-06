package Hospedaje.Tests;

import Hospedaje.Habitaciones.GestionHabitaciones;
import Hospedaje.Habitaciones.Habitacion;

import java.util.List;

public class HabitacionesTest {
    public static void main(String[] args) {
        GestionHabitaciones gestion_habitaciones = new GestionHabitaciones();
        // Habitaciones Totales
        List<Habitacion> habitaciones = gestion_habitaciones.getHabitaciones();
        System.out.println("Habitaciones totales: " + habitaciones.size());
        habitaciones.forEach(habitacion -> {
            System.out.println(habitacion.getInfo());
        });
    }
}
