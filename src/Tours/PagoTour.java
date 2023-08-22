public package Tours;

import Reservas.Reserva;

class PagoTour {

    private float tarifaImpuesto = 0.12;
    private float tarifaDevolucion = 0.4;
    private Reserva Reserva;

    public PagoTour(Reserva reserva) {
        this.Reserva = reserva;
    }

    public float calcularPrecioNeto() {
        Arraylist<Tour> tours = this.Reserva.getToursAgregados();
        float precioNeto = 0;
        for (Tour tour : tours) {
            precioFinal += tour.getPrecio();
        }

        return precioNeto;
    }

    public float calcularPrecioFinal() {
        return this.calcularPrecioNeto() + this.calcularImpuesto();
    }

    public calcularImpuesto() {
        return this.calcularPrecioNeto() * this.tarifaImpuesto;
    }

    public calcularDevolucion() {
        int dias = this.Reserva.tiempoTrasCancelar();
        if(dias <= 15){
            return this.calcularPrecioFinal();
        }else if (dias > 15 && dias <= 30){
            return this.calcularPrecioFinal() * this.tarifaDevolucion;
        }

        return 0;
    }
}

    