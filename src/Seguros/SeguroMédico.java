package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.Date;
import javax.swing.JOptionPane;

public class SeguroMédico extends Seguro {
    private String[] coberturas;
    private int nivelSeguro;

    public SeguroMédico(Usuario propietario, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, float primaSinRecargo, String[] coberturas, int nivelSeguro) {
        super(propietario, beneficiarios, fechaDeInicio, fechaDeVencimiento, primaSinRecargo);
        this.coberturas = coberturas;
        this.nivelSeguro = nivelSeguro;
    }

    void indemnizar(float valorGastado, String motivo) {
        float porcentajeCubierto = 0.0F;
        String[] var4 = this.coberturas;
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String cobertura = var4[var6];
            if (motivo.equalsIgnoreCase(cobertura)) {
                if (this.nivelSeguro == 1) {
                    porcentajeCubierto = (float)((double)valorGastado * 0.4);
                } else if (this.nivelSeguro == 2) {
                    porcentajeCubierto = (float)((double)valorGastado * 0.6);
                } else if (this.nivelSeguro == 3) {
                    porcentajeCubierto = valorGastado;
                }
                break;
            }
        }

        JOptionPane.showMessageDialog((Component)null, "Te daremos: " + porcentajeCubierto + "$");
    }

    float calcularPrimaTotal() {
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
