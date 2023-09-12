package Vehiculo;

import Pagos.Pago;

public class PagoReservaVehiculos {
    private final double montoAPagar;
    private final String modoPago;
    private final double impuesto = 12/100;
    private  double descuento = 0;
    private Pago pago;

    public PagoReservaVehiculos(double montoAPagar, String modoPago) {
        this.montoAPagar = montoAPagar;
        this.modoPago = modoPago;
    }

    private double calcularValorTotal() {
        double valorTotal = montoAPagar;
        valorTotal = valorTotal-valorTotal*descuento/100;
        valorTotal *= 1+impuesto;
        return valorTotal;
    }

    public void pagar() {
        pago = new Pago(calcularValorTotal(), modoPago);
        pago.pagar();
    }
}
