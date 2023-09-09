//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Seguros;

import Pagos.Pago;
import Principal.Usuario;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public abstract class Seguro {
    private Usuario propietario;
    private String[] beneficiarios;
    private Date fechaDeInicio;
    private Date fechaDeVencimiento;
    private float primaSinRecargo;
    //Se colocó un atributo estado para determinar si el seguro esta activo, inactivo o
    // si el cliente ya cobró la indemnización.
    private String estado;

    //Sirve para inicializar un seguro sin atributos más que el estado en el que está.
    public Seguro(String estado){
        this.estado = estado;
    }

    //Se eliminaron las condiciones. No eran relevantes en el sistema.
    public Seguro(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, String estado) {
        this.propietario = propietario;
        this.beneficiarios = beneficiarios;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.estado = estado;
    }

    //Es una nueva forma de renovar el seguro. SOLO lo renueva por un 1 mes.
    //Además coloca al seguro en estado activo.
    public void newRenovar() {
        Date fechaActual = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaActual);
        calendario.add(Calendar.MONTH, 1);
        Date fechaVencimiento = calendario.getTime();
        this.fechaDeVencimiento = fechaVencimiento;
        this.setEstado("Activo");
        JOptionPane.showMessageDialog(null, "Tu seguro se ha renovado exitosamente un mes más.", "Éxito", JOptionPane.WARNING_MESSAGE);
    }


    //Ya no es necesario el monto a pagar porque siempre será la prima total. Y la fecha tampoco,
    //porque, se asume que es la fecha actual.
    public void pagar(String modoPago) {
        Pago pagoSeguros = new Pago(this.calcularPrimaTotal(), modoPago, new Date());
        pagoSeguros.pagar();
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

    public String getEstado() {return estado;}

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
