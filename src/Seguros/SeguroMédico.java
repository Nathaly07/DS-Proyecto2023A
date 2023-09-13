package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.Date;
import javax.swing.JOptionPane;

public class SeguroMédico extends Seguro {
    private String[] coberturas;
    private int nivelSeguro;


    //Ya se coloca un valor fijo en la prima sin recargo.
    public SeguroMédico(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, String[] coberturas, int nivelSeguro, String estado) {
        super(propietario, beneficiarios, fechaDeInicio, fechaDeVencimiento, estado);
        super.setPrimaSinRecargo(200.0f);
        this.coberturas = coberturas;
        this.nivelSeguro = nivelSeguro;
    }

    @Override
    public void indemnizar(float valorGastado, String motivo) {
        float porcentajeCubierto = 0.0f;
        boolean estado = false;
        for (String cobertura : coberturas) {
            if (motivo.equalsIgnoreCase(cobertura)) {
                if (nivelSeguro == 1) {
                    porcentajeCubierto = (float) (valorGastado * 0.4);
                } else if (nivelSeguro == 2) {
                    porcentajeCubierto = (float) (valorGastado * 0.6);
                } else if (nivelSeguro == 3) {
                    porcentajeCubierto = valorGastado;
                }
                estado = true;
            }
        }

        if (estado) {
            JOptionPane.showMessageDialog(null, "Tu seguro si cubre tu situación.\nTe daremos: "+porcentajeCubierto+" $. ", "Solicitud aceptada", JOptionPane.INFORMATION_MESSAGE);
            this.setEstado("Cobrado");
        } else {
            JOptionPane.showMessageDialog(null, "Lo sentimos mucho.\nTu seguro médico no cubre ese problema.", "Una disculpa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public float calcularPrimaTotal() {
        float total = 0.0F;
        if (this.nivelSeguro == 1) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.15) * (float)this.getBeneficiarios().length;
        } else if (this.nivelSeguro == 2) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.3) * (float)this.getBeneficiarios().length;
        } else if (this.nivelSeguro == 3) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.45) * (float)this.getBeneficiarios().length;
            return total;
        }

        return total;
    }
}
