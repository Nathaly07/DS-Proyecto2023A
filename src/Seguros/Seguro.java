package Seguros;

import java.util.Date;

public abstract class Seguro {
    private int ID_Seguro;
    private String propietario;
    private String[] condiciones;
    private String[] beneficiarios;
    private Date fechaDeInicio;
    private Date fechaDeVencimiento;
    private float primaSinRecargo;

    public Seguro(int ID_Seguro, String propietario, String[] condiciones, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, float primaSinRecargo) {
        this.ID_Seguro = ID_Seguro;
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

    public void cancelar(int ID_Seguro) {
        this.ID_Seguro = 0;
    }

    abstract float indemnizar(float valorGastado, String motivo);

    abstract float calcularPrimaTotal();

    public int getID_Seguro() {
        return ID_Seguro;
    }

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
