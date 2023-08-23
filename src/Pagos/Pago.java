package Pagos;


import javax.swing.*;

public class Pago {
    private double montoAPagar;
    private String modoPago; //"Paypal", "Transferencia", "Tarjeta de Credito"

    public Pago(double montoAPagar, String modoPago) {
        this.montoAPagar = montoAPagar;
        this.modoPago = modoPago;
    }


    public void pagar(){
        JOptionPane.showMessageDialog(null, "Se ha pagado la cantidad de: " + montoAPagar + " por medio de: "
        + modoPago);
    }

}
