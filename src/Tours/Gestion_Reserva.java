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
    public void actualizar(){

    }
    public void totalPersonasPorTour(Tour tour){

    }
    public void agregarReserva(){

    }
}