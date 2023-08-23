package Hospedaje.Pagos;

import Hospedaje.Reservas.ReservaHospedaje;
import Pagos.Pago;

import javax.swing.*;

public class PagoHospedaje extends Pago {
    private ReservaHospedaje reservaHospedaje;

    public PagoHospedaje(ReservaHospedaje reservaHospedaje, String metodoPago) {
        super(calcularMontoTotal(reservaHospedaje), metodoPago);
        this.reservaHospedaje = reservaHospedaje;
    }

    private static double calcularMontoTotal(ReservaHospedaje reserva) {
        long dias = (reserva.getFechaFin().getTime() - reserva.getFechaInicio().getTime()) / (24 * 60 * 60 * 1000);
        double montoTotal = dias * reserva.getHabitaciones().length * reserva.getHabitaciones()[0].getPrecioPorNoche();
        return montoTotal;
    }

    @Override
    public void pagar() {
    }
}

