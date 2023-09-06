package Hospedaje.Reservas;

import Hospedaje.Criteria.CriteriaDisponibilidadHabitaciones;
import Hospedaje.Criteria.CriteriaDoble;
import Hospedaje.Habitaciones.GestionHabitaciones;
import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Pagos.PagoHospedaje;
import Principal.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GestionReservas {
    private final List<ReservaHospedaje> reservas;
    private final GestionHabitaciones gestionHabitaciones;

    public GestionReservas(GestionHabitaciones gestionHabitaciones) {
        this.gestionHabitaciones = gestionHabitaciones;
        //this.reservas = new ArrayList<ReservaHospedaje>();
        this.reservas = this.leer();
    }
    public List<Habitacion> getHabitacionesDisponibles(Date reservarDesde, Date reservarHasta) {
        List<Habitacion> habitaciones = this.gestionHabitaciones.getHabitaciones();
        CriteriaDoble criteriaDisponibles = new CriteriaDisponibilidadHabitaciones(reservarDesde, reservarHasta);
        return criteriaDisponibles.meetCriteria(habitaciones, this.reservas);
    }
    public List<ReservaHospedaje> getReservas() {
        return reservas;
    }

    public boolean crearReserva(ReservaHospedaje reserva) {
        this.reservas.add(reserva);
        this.guardar();
        return true; // Reserva creada exitosamente
    }

    public boolean cancelarReserva(String reservaId) {
        for (ReservaHospedaje reserva : reservas) {
            if (reserva.getReservaId().equals(reservaId)) {
                this.reservas.remove(reserva);
                this.guardar();
                return true; // Reserva cancelada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }

    public boolean actualizarReserva(String reservaId, ReservaHospedaje nuevaReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getReservaId().equals(reservaId)) {
                this.reservas.set(i, nuevaReserva);
                this.guardar();
                return true; // Reserva actualizada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }

    public boolean eliminarReserva(ReservaHospedaje reserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getReservaId().equals(reserva.getReservaId())) {
                this.reservas.remove(i);
                this.guardar();
                return true; // Reserva eliminada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }

    public boolean confirmarReserva(ReservaHospedaje reservaAConfirmar, String metodoPago) {
        for (ReservaHospedaje reserva : reservas) {
            if (reserva == reservaAConfirmar) {
                PagoHospedaje pago = new PagoHospedaje(reserva, metodoPago);
                pago.pagar();
                reserva.confirmarReserva();
                this.guardar();
                return true; // Reserva confirmada exitosamente
            }
        }
        return false; // Reserva no encontrada
    }

    public List<ReservaHospedaje> getReservasPorUsuario(Usuario usuario) {
        List<ReservaHospedaje> reservasDelUsuario = new ArrayList<ReservaHospedaje>();
        for (ReservaHospedaje reserva : reservas) {
            if (reserva.getUsuario().getEmail().equals(usuario.getEmail())) {
                reservasDelUsuario.add(reserva);
            }
        }
        return reservasDelUsuario;
    }


    private void guardar() {
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath.concat("/src/Hospedaje/data/reservas.txt");
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this.reservas);
            outputStream.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private List<ReservaHospedaje> leer() {
        List<ReservaHospedaje> reservasGuardadas = null;
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath.concat("/src/Hospedaje/data/reservas.txt");
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            reservasGuardadas = (List<ReservaHospedaje>) inputStream.readObject();
            inputStream.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }

        return reservasGuardadas;
    }
}
