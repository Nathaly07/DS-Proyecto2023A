package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.Date;
import javax.swing.JOptionPane;

public class SeguroDeViajes extends Seguro {
    private int destino;
    private String[][] coberturas;

    public SeguroDeViajes(String estado){
        super(estado);
    }

    public SeguroDeViajes(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, int destino, String[][] coberturas, String estado) {
        super(propietario, beneficiarios, fechaDeInicio, fechaDeVencimiento, estado);
        super.setPrimaSinRecargo(250.0f);
        this.destino = destino;
        this.coberturas = coberturas;
    }


    @Override
    void indemnizar(float valorGastado, String motivo) {
        float cantidadAPagar = 0.0f;
        for (String[] cobertura : coberturas) {
            if (motivo.equalsIgnoreCase(cobertura[0])) {
                if ((valorGastado <= Float.parseFloat(cobertura[1])) && (valorGastado > 0)) {
                    cantidadAPagar = valorGastado;
                } else {
                    cantidadAPagar = Float.parseFloat(cobertura[1]);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Te daremos: " + cantidadAPagar + "$");
    }

    float calcularPrimaTotal() {
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

    public int getDestino() {
        return this.destino;
    }

    public String[][] getCoberturas() {
        return this.coberturas;
    }
}
