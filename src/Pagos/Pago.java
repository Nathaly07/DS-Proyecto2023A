package Pagos;


import javax.swing.*;
import java.util.Date;

public class Pago {
    private double montoAPagar;
    private String modoPago; //"Paypal", "Transferencia", "Tarjeta de Credito"
    private Date FechaPago;

    public Pago(double montoAPagar, String modoPago) {
        this.montoAPagar = montoAPagar;
        this.modoPago = modoPago;
    }

    public Pago(double montoAPagar, String modoPago, Date fechaPago) {
        this.montoAPagar = montoAPagar;
        this.modoPago = modoPago;
        this.FechaPago = fechaPago;
    }

    public Pago() {

    }

    public void pagar() {
        JOptionPane.showMessageDialog(null, "Se ha pagado la cantidad de: " + montoAPagar + " por medio de: "
                + modoPago);
    }

}
