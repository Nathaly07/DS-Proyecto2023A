package Seguros;

import Pagos.Pago;

import javax.swing.*;
import java.util.Date;

public abstract class Seguro {
    //Brynator
    private String propietario;
    private String[] condiciones;
    private String[] beneficiarios;
    private Date fechaDeInicio;
    private Date fechaDeVencimiento;
    private float primaSinRecargo;

    public Seguro(String propietario, String[] condiciones, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, float primaSinRecargo) {
        this.propietario = propietario;
        this.condiciones = condiciones;
        this.beneficiarios = beneficiarios;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.primaSinRecargo = primaSinRecargo;
    }

    public boolean renovar(Date fecha) {
        if (fecha.after(fechaDeVencimiento)) {
            this.fechaDeVencimiento = fecha;
            return true;
        } else {
            return false;
        }
    }

    public void pagar(double montoAPagar, String modoPago, Date fechaPago ) {
        if (montoAPagar == 0) {
            JOptionPane.showMessageDialog(null, "Pago no realizado");
        } else {
            Pago pagoSeguros = new Pago(montoAPagar,modoPago,fechaPago);
            pagoSeguros.pagar();
        }
    }

    abstract void indemnizar(float valorGastado, String motivo);

    abstract float calcularPrimaTotal();

    public String getPropietario() {
        return propietario;
    }

    public String[] getCondiciones() {
        return condiciones;
    }

    public String[] getBeneficiarios() {
        return beneficiarios;
    }

    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }

    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public float getPrimaSinRecargo() {
        return primaSinRecargo;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }
}
