//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Seguros;

import Pagos.Pago;
import Principal.Usuario;
import java.awt.Component;
import java.util.Date;
import javax.swing.JOptionPane;

public abstract class Seguro {
    private Usuario propietario;
    private String[] beneficiarios;
    private Date fechaDeInicio;
    private Date fechaDeVencimiento;
    private float primaSinRecargo;
    private int estado;

    public Seguro(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, int estado) {
        this.propietario = propietario;
        this.beneficiarios = beneficiarios;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.estado = estado;
    }

    public boolean renovar(Date fecha) {
        if (fecha.after(this.fechaDeVencimiento)) {
            this.fechaDeVencimiento = fecha;
            return true;
        } else {
            return false;
        }
    }

    public void pagar(double montoAPagar, String modoPago, Date fechaPago) {
        if (montoAPagar == 0.0) {
            JOptionPane.showMessageDialog((Component)null, "Pago no realizado");
        } else {
            Pago pagoSeguros = new Pago(montoAPagar, modoPago, fechaPago);
            pagoSeguros.pagar();
        }

    }

    abstract void indemnizar(float var1, String var2);

    abstract float calcularPrimaTotal();

    public Usuario getPropietario() {
        return this.propietario;
    }

    public String[] getBeneficiarios() {
        return this.beneficiarios;
    }

    public Date getFechaDeInicio() {
        return this.fechaDeInicio;
    }

    public Date getFechaDeVencimiento() {
        return this.fechaDeVencimiento;
    }

    public float getPrimaSinRecargo() {
        return this.primaSinRecargo;
    }
    public void setPrimaSinRecargo(float valor) {
        this.primaSinRecargo = valor ;
    }

    public int getEstado() {
        return estado;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }
}
