package Tours;
import Reservas.Reserva;
import Reservas.ReservaTour;

import java.util.ArrayList;
import java.util.List;

public class Gestion_Reserva {

    private List<ReservaTour> reservas;

    public Gestion_Reserva() {
        this.reservas = new ArrayList<>();
    }

    public void cancelar(String id){
        ReservaTour reservaACancelar = null;
        for (ReservaTour reserva : this.reservas) {
            if (reserva.getReservaID().equalsIgnoreCase(id)) {
                reservaACancelar = reserva;
                break;
            }
        }
        if (reservaACancelar != null) {
            this.reservas.remove(reservaACancelar);
        }
    }

    public void actualizar(String id, ReservaTour changes){
        for (ReservaTour reserva : this.reservas) {
            if (reserva.getReservaID().equalsIgnoreCase(id)) {
                // Actualizar los atributos necesarios según los cambios
                reserva.setActivado(changes.isActivado());
                reserva.setNumeroPersonas(changes.getNumeroPersonas());
                reserva.setSeguroActivado(changes.isSeguroActivado());
                reserva.setFechaCreacion(changes.getFechaCreacion());
                reserva.setFechaConfirmacion(changes.getFechaConfirmacion());
                reserva.setToursAgregados(changes.getToursAgregados());
                reserva.setGestionTour(changes.getGestionTour());
                break;
            }
        }
    }

    public void totalPersonasPorTour(Tour tour){

    }

    public void agregarReserva(ReservaTour reservaAAgregar) { this.reservas.add(reservaAAgregar); }
}