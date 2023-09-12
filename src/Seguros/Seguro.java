//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Seguros;

import Pagos.Pago;
import Principal.Usuario;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public abstract class Seguro {
    private Usuario propietario;
    private String[] beneficiarios;
    private Date fechaDeInicio;
    private Date fechaDeVencimiento;
    private float primaSinRecargo;
    private String estado;

    //Constructor del seguro
    public Seguro(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, String estado) {
        this.propietario = propietario;
        this.beneficiarios = beneficiarios;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.estado = estado;
    }

    //Método para renovar el seguro. Lo coloca como activo, y cambia su fecha de vencimiento. 
    public void renovar() {
        Date fechaActual = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaActual);
        calendario.add(Calendar.MONTH, 1);
        Date fechaVencimiento = calendario.getTime();
        this.fechaDeVencimiento = fechaVencimiento;
        this.setEstado("Activo");
        JOptionPane.showMessageDialog(null, "Tu seguro se ha renovado exitosamente un mes más.", "Éxito", JOptionPane.WARNING_MESSAGE);
    }


    //Método para pagar el seguro.
    public void pagar(String modoPago) {
        Pago pagoSeguros = new Pago(this.calcularPrimaTotal(), modoPago, new Date());
        pagoSeguros.pagar();
    }

    //Métodos abstractos. Varia dependiendo del tipo de seguro.
    public abstract void indemnizar(float valorGastado, String motivo);

    public abstract float calcularPrimaTotal();

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

    public String getEstado() {return estado;}

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
