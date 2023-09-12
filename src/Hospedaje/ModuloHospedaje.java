package Hospedaje;

import Hospedaje.Habitaciones.GestionHabitaciones;
import Hospedaje.Reservas.GestionReservas;

public class ModuloHospedaje {
    private GestionHabitaciones gestionHabitaciones;
    private GestionReservas gestionReservas;

    public ModuloHospedaje() {
        this.gestionHabitaciones = new GestionHabitaciones();
        this.gestionReservas = new GestionReservas(gestionHabitaciones);
    }

    public GestionReservas getGestionReservas() {
        return gestionReservas;
    }

    public GestionHabitaciones getGestionHabitaciones() {
        return gestionHabitaciones;
    }

    // En una proxima iteracion gestion hoteles
}
