package Hospedaje.Reservas;

import Hospedaje.Criteria.CriteriaDisponibilidadHabitaciones;
import Hospedaje.Criteria.HospedajeCriteria;
import Hospedaje.Habitaciones.GestionHabitaciones;
import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Pagos.PagoHospedaje;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionReservas {
    private final String filePath = "src/Hospedaje/Reservas/data/reservas.txt";
    private List<ReservaHospedaje> reservas;
    private GestionHabitaciones gestion_habitaciones;

    public GestionReservas(GestionHabitaciones gestion_habitaciones) {
        this.gestion_habitaciones = gestion_habitaciones;
        this.reservas = new ArrayList<>();
        Date fechaCreacion = new Date();
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        Habitacion habitacion1 = gestion_habitaciones.getHabitaciones().get(1);
        Habitacion habitacion2 = gestion_habitaciones.getHabitaciones().get(2);
        Habitacion[] habitaciones = {habitacion1, habitacion2};

        this.reservas.add(new ReservaHospedaje("1" , "1", 2, habitaciones, fechaCreacion, fechaInicio, fechaFin));
    }

    public List<Habitacion> getHabitacionesDisponibles(Date reservarDesde, Date reservarHasta) {
        List<Habitacion> habitaciones = this.gestion_habitaciones.getHabitaciones();
        HospedajeCriteria criteriaDisponibles = new CriteriaDisponibilidadHabitaciones(reservarDesde, reservarHasta);
        return criteriaDisponibles.meetCriteria(habitaciones, reservas);
    }
    public List<ReservaHospedaje> getReservas() {
        return reservas;
    }

    public boolean crearReserva(ReservaHospedaje reserva) {
        this.reservas.add(reserva);
        return true; // Reserva creada exitosamente
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

    public boolean confirmarReserva(String reservaId, String metodoPago) {
        for (ReservaHospedaje reserva : reservas) {
            if (reserva.getReservaId().equals(reservaId)) {
                PagoHospedaje pago = new PagoHospedaje(reserva, metodoPago);
                pago.pagar();
                reserva.confirmarReserva();
                return true; // Reserva confirmada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }


    private void serialize(GestionReservas user) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(user);
            outputStream.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private GestionReservas deserialize() {
        GestionReservas gestionReservas = null;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            gestionReservas = (GestionReservas) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }

        return gestionReservas;
    }
}
