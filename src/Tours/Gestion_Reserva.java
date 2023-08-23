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

    public int totalPersonasPorTour(String idTour){
        int contadorPersonas = 0;
        for (ReservaTour reserva : this.reservas) {
            for (Tour tour : reserva.getGestionTour().getTours()){
                if (tour.getTourID().equalsIgnoreCase(idTour)) {
                    contadorPersonas += reserva.getNumeroPersonas();
                }
            }
        }
        return contadorPersonas;
    }

    public void agregarReserva(ReservaTour reservaAAgregar) { this.reservas.add(reservaAAgregar); }

    public Reserva getReserva(String idReserva){
        for (Reserva reserva: this.reservas){
            if(reserva.getReservaID().equalsIgnoreCase(idReserva)) {
                return reserva;
            }
        }
        return null;
    }

}