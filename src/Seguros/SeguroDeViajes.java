package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.Date;
import javax.swing.JOptionPane;

public class SeguroDeViajes extends Seguro {
    private int destino;
    private String[][] coberturas;


    //Ya se coloca un valor fijo en la prima sin recargo.
    public SeguroDeViajes(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, int destino, String[][] coberturas, String estado) {
        super(propietario, beneficiarios, fechaDeInicio, fechaDeVencimiento, estado);
        super.setPrimaSinRecargo(250.0f);
        this.destino = destino;
        this.coberturas = coberturas;
    }

    @Override
    public void indemnizar(float valorGastado, String motivo) {
        float cantidadAPagar = 0.0f;
        boolean estado = false;
        for (String[] cobertura : coberturas) {
            if (motivo.equalsIgnoreCase(cobertura[0])) {
                if ((valorGastado <= Float.parseFloat(cobertura[1])) && (valorGastado > 0)) {
                    cantidadAPagar = valorGastado;
                } else {
                    cantidadAPagar = Float.parseFloat(cobertura[1]);
                }
                estado = true;
            }
        }

        if (estado) {
            JOptionPane.showMessageDialog(null, "Tu seguro si cubre tu situación.\nTe daremos: "+cantidadAPagar+" $. ", "Solicitud aceptada", JOptionPane.INFORMATION_MESSAGE);
            this.setEstado("Cobrado");
        } else {
            JOptionPane.showMessageDialog(null, "Lo sentimos mucho.\nTu seguro de viaje no cubre esa situación.", "Una disculpa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public float calcularPrimaTotal() {
        float total = 0.0F;
        if (this.destino == 1) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.1) * (float)this.getBeneficiarios().length;
        } else if (this.destino == 2) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.2) * (float)this.getBeneficiarios().length;
        } else if (this.destino == 3) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.3) * (float)this.getBeneficiarios().length;
        } else if (this.destino == 4) {
            total = (float)((double)this.getPrimaSinRecargo() + (double)this.getPrimaSinRecargo() * 0.4) * (float)this.getBeneficiarios().length;
        }

        return total;
    }
}
