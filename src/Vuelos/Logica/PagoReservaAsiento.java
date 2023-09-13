package Vuelos.Logica;


import Pagos.Pago;


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
        double total = this.reserva.generarCostoTotal() * 0.12;
        double truncatedTotal = Math.floor(total * 100) / 100;
        return truncatedTotal;
    }

    public void pagar(String metodoPago){
        this.reserva.cambiarEstado(EstadoReserva.Pagado);
        float precioFinal = Float.parseFloat(calcularCostoTotalReservados()+"");
        Pago pago = new Pago(precioFinal, metodoPago);
        pago.pagar();
    }
}
