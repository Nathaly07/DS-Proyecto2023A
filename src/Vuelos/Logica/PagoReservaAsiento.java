package Vuelos.Logica;

import javax.swing.*;

public class PagoReservaAsiento {
    private double monto;
    private ReservaAsiento reserva;


    public PagoReservaAsiento(ReservaAsiento reservas){

        this.reserva = reservas;
        monto = calcularCostoTotalReservados();
    }


    public double calcularCostoTotalReservados(){
        return this.reserva.generarCostoTotal() + calcularIVA();
    }


    //IVA
    public double calcularIVA(){
        double total = 0;
        total = this.reserva.generarCostoTotal()* 0.12;
        return total;
    }

    public void Pagar(){
        this.reserva.CambiarEstado(EstadoReserva.Pagado);
        JOptionPane.showMessageDialog(null, "Pago realizado con exito", "Aviso de pago", JOptionPane.INFORMATION_MESSAGE);
    }
}
