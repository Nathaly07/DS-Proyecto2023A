package Tours;

import Reservas.Reserva;

public class ModuloTours {
    private Reservas.Reserva reserva;

    public ModuloTours(Reserva reserva) {
        this.reserva = reserva;
    }

    public void crearReserva(){
        this.reserva = new Reserva() {
            @Override
            public void cancelarReserva() {

            }

            @Override
            public void modificarReserva() {

            }
        }
    }

    public void cancelarReserva(){
        this.reserva.cancelarReserva();
    }

    public void cambiarReserva(){

    }
}
