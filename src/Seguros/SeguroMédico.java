package Seguros;

import javax.swing.*;
import java.util.Date;

public class SeguroMédico extends Seguro {
    private String[] coberturas;
    private int nivelSeguro;

    public SeguroMédico(String propietario, String[] condiciones, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, float primaSinRecargo, String[] coberturas, int nivelSeguro) {
        super(propietario, condiciones, beneficiarios, fechaDeInicio, fechaDeVencimiento, primaSinRecargo);
        this.coberturas = coberturas;
        this.nivelSeguro = nivelSeguro;
    }

    @Override
    void indemnizar(float valorGastado, String motivo) {
        float porcentajeCubierto = 0.0f;
        for (String cobertura : coberturas) {
            if (motivo.equalsIgnoreCase(cobertura)) {
                if (nivelSeguro == 1) {
                    porcentajeCubierto = (float) (valorGastado * 0.4);
                } else if (nivelSeguro == 2) {
                    porcentajeCubierto = (float) (valorGastado * 0.6);
                } else if (nivelSeguro == 3) {
                    porcentajeCubierto = valorGastado;
                }
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "Te daremos: " + porcentajeCubierto + "$");
    }

    float calcularPrimaTotal() {
        float total = 0;

        if (nivelSeguro == 1) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.15) * getBeneficiarios().length;
        } else if (nivelSeguro == 2) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.30) * getBeneficiarios().length;
        } else if (nivelSeguro == 3) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.45) * getBeneficiarios().length;
            return total;
        }
        return total;
    }

}
