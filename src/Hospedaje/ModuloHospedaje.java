package Hospedaje;

import Hospedaje.Habitaciones.Gestion_Habitaciones;
import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.Gestion_Reservas;
import Hospedaje.Reservas.ReservaHospedaje;

import java.util.Date;
import java.util.List;

public class ModuloHospedaje {
    private Gestion_Habitaciones gestion_habitaciones;
    private Gestion_Reservas gestion_reservas;

    public ModuloHospedaje() {
        this.gestion_habitaciones = new Gestion_Habitaciones();
        this.gestion_reservas = new Gestion_Reservas(gestion_habitaciones);
    }

    public boolean crearReserva(ReservaHospedaje reserva) {
        return this.gestion_reservas.crearReserva(reserva);
    }

    public boolean actualizarReserva(String reservaId, ReservaHospedaje nuevaReserva) {
        return this.gestion_reservas.actualizarReserva(reservaId, nuevaReserva);
    }

    public boolean cancelarReserva(String reservaId) {
        return this.gestion_reservas.cancelarReserva(reservaId);
    }

    public boolean confirmarReserva(String reservaID, String metodoPago) {
        return this.gestion_reservas.confirmarReserva(reservaID, metodoPago);
    }

    public List<Habitacion> buscarHabitacionesDisponibles(Date reservarDesde, Date reservarHasta) {
        return this.gestion_reservas.getHabitacionesDisponibles(reservarDesde, reservarHasta);
    }
}
