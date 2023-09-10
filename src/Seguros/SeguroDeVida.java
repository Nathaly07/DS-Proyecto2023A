package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.Date;
import javax.swing.JOptionPane;

public class SeguroDeVida extends Seguro {
    private int nivelSeguro;



    //Ya se coloca un valor fijo en la prima sin recargo.
    public SeguroDeVida(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento,int nivelSeguro, String estado) {
        super(propietario, beneficiarios, fechaDeInicio, fechaDeVencimiento, estado);
        super.setPrimaSinRecargo(400.0f);
        this.nivelSeguro = nivelSeguro;
    }

    void indemnizar(float valorPedido, String motivo) {
        float totalMonto = 0.0F;
        if (valorPedido <= 400000.0F && motivo.equalsIgnoreCase("muerte")) {
            if (this.nivelSeguro == 1) {
                totalMonto = 100000.0F;
            } else if (this.nivelSeguro == 2) {
                totalMonto = 200000.0F;
            } else if (this.nivelSeguro == 3) {
                totalMonto = 300000.0F;
            } else if (this.nivelSeguro == 4) {
                totalMonto = 400000.0F;
            }
            JOptionPane.showMessageDialog(null, "Tu seguro si cubre tu situación.\nTe daremos: "+totalMonto+" $. ", "Solicitud aceptada", JOptionPane.INFORMATION_MESSAGE);
            this.setEstado("Cobrado");
        }else{
            JOptionPane.showMessageDialog(null, "Lo sentimos mucho.\nTu seguro de vida no lo cubre.", "Una disculpa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    float calcularPrimaTotal() {
        float total = 0.0F;
        if (this.nivelSeguro == 1) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.4) * (float)this.getBeneficiarios().length;
        } else if (this.nivelSeguro == 2) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.45) * (float)this.getBeneficiarios().length;
        } else if (this.nivelSeguro == 3) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.5) * (float)this.getBeneficiarios().length;
        } else if (this.nivelSeguro == 4) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.6) * (float)this.getBeneficiarios().length;
        }

        return total;
    }
}
