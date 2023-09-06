package Tours;

import Pagos.Pago;
import Reservas.ReservaTour;

import javax.swing.*;
import java.util.ArrayList;

public class PagoReserva {

    private double tarifaImpuesto = 0.12;
    private double tarifaDevolucion = 0.4;
    private ReservaTour Reserva;

    public PagoReserva(ReservaTour reserva) {
        this.Reserva = reserva;
    }

    public float calcularPrecioNeto(ArrayList<Tour> tours) {
        float precioNeto = 0;
        for (Tour tour : tours) {
            precioNeto += tour.getPrecio();
        }

        return precioNeto;
    }

    public float calcularPrecioFinal(ArrayList<Tour> tours) {
        float precioNeto = this.calcularPrecioNeto(tours);
        return precioNeto + this.calcularImpuesto(precioNeto);
    }

    public float calcularImpuesto(float precioNeto)
    {
        return (float)(precioNeto * this.tarifaImpuesto);
    }

    public void calcularDevolucion(int diasAntesInicioTour, ArrayList<Tour> tours) {
        float precioFinal = this.calcularPrecioFinal(tours);
        float devolucion;
        if(diasAntesInicioTour >= 20){
            devolucion = precioFinal * 0.8f;
            JOptionPane.showMessageDialog(null, "Se le ha devuelto: " + devolucion, "Devolución", JOptionPane.INFORMATION_MESSAGE);
        } else if(diasAntesInicioTour >= 10){
            devolucion = precioFinal * 0.5f;
            JOptionPane.showMessageDialog(null, "Se le ha devuelto: " + devolucion, "Devolución", JOptionPane.INFORMATION_MESSAGE);
        }else if(diasAntesInicioTour >= 5){
            devolucion = precioFinal * 0.2f;
            JOptionPane.showMessageDialog(null, "Se le ha devuelto: " + devolucion, "Devolución", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "No hay devolucion", "Devolución", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void pagar(ArrayList<Tour> tours, String metodoPago){
        float precioFinal = this.calcularPrecioFinal(tours);
        Pago pago = new Pago(precioFinal, metodoPago);
        pago.pagar();
    }
}

    