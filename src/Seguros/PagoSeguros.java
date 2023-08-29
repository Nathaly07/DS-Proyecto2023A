package Seguros;

import javax.swing.*;
import java.util.Date;

public class PagoSeguros {
    private float monto;
    static int pagos_realizados;
    private Date fechaPago;
    private Seguro seguro;

    public PagoSeguros(Seguro seguro, Date fechaPago) {
        this.seguro = seguro;
        this.monto = seguro.calcularPrimaTotal();
        this.fechaPago = fechaPago;
    }

    public float getMonto() {
        return monto;
    }

    public void cobrarSeguro() {
        pagos_realizados += 1;
        JOptionPane.showMessageDialog(null, "Seguro cobrado exitosamente");
    }

}
