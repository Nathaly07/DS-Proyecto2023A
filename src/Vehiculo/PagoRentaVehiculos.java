package Vehiculo;

import Pagos.Pago;

public class PagoRentaVehiculos  {
    private final double montoAPagar;
    private final String modoPago;
    private final double impuesto = 12;
    private Pago pago;

    public PagoRentaVehiculos(double montoAPagar, String modoPago) {
        this.montoAPagar = montoAPagar;
        this.modoPago = modoPago;
    }

    private double calcularValorTotal() {
        return montoAPagar*1.12;
    }

    public void pagar() {
        pago = new Pago(calcularValorTotal(),modoPago);
        pago.pagar();
    }
}
