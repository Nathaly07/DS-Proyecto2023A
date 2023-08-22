package Hospedaje.Reservas;

import Hospedaje.Habitaciones.Gestion_Habitaciones;
import Hospedaje.Habitaciones.Habitacion;
import Reservas.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion_Reservas {
    private List<ReservaHospedaje> reservas;

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

    public List<ReservaHospedaje> getReservas() {
        return reservas;
    }
}
