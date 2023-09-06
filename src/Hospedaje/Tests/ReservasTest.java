package Hospedaje.Tests;

import Hospedaje.Habitaciones.Gestion_Habitaciones;
import Hospedaje.Reservas.Gestion_Reservas;
import Hospedaje.Reservas.ReservaHospedaje;

import java.util.List;

public class ReservasTest {
    public static void main(String[] args) {
        Gestion_Habitaciones gestion_habitaciones = new Gestion_Habitaciones();
        Gestion_Reservas gestion_reservas = new Gestion_Reservas(gestion_habitaciones);

        // Reservas Totales
        List<ReservaHospedaje> reservas = gestion_reservas.getReservas();
        System.out.println("Habitaciones totales: " + reservas.size());
        reservas.forEach(reserva -> {
            System.out.println(reserva.getInfo());
        });
    }
}
