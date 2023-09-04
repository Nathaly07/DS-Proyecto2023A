package Tours;

import Reservas.ReservaTour;

import java.util.ArrayList;

public class PagoReserva {

    private double tarifaImpuesto = 0.12;
    private double tarifaDevolucion = 0.4;
    private ReservaTour Reserva;

    public PagoReserva(ReservaTour reserva) {
        this.Reserva = reserva;
    }

    public float calcularPrecioNeto() {
        ArrayList<Tour> tours = this.Reserva.getToursAgregados();
        float precioNeto = 0;
        for (Tour tour : tours) {
            precioNeto += tour.getPrecio();
        }

        return precioNeto;
    }

    public float calcularPrecioFinal() {
        return this.calcularPrecioNeto() + this.calcularImpuesto();
    }

    public float calcularImpuesto() {
        return (float)(this.calcularPrecioNeto() * this.tarifaImpuesto);
    }

    public void calcularDevolucion(int diasAntesInicioTour) {


    }
}

    