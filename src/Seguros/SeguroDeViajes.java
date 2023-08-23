package Seguros;

import java.util.Date;

public class SeguroDeViajes extends Seguro {
    private int destino;
    private String[][] coberturas;

    public SeguroDeViajes(int ID_Seguro, String propietario, String[] condiciones, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, float primaSinRecargo, int destino, String[][] coberturas) {
        super(ID_Seguro, propietario, condiciones, beneficiarios, fechaDeInicio, fechaDeVencimiento, primaSinRecargo);
        this.destino = destino;
        this.coberturas = coberturas;
    }


    @Override
    float indemnizar(float valorGastado, String motivo) {
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
        return cantidadAPagar;
    }

    float calcularPrimaTotal() {
        float total = 0;
        if (destino == 1) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.1) * getBeneficiarios().length;
        } else if (destino == 2) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.2) * getBeneficiarios().length;
        } else if (destino == 3) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.3) * getBeneficiarios().length;
        } else if (destino == 4) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.4) * getBeneficiarios().length;
        }
        return total;
    }

    public int getDestino() {
        return destino;
    }

    public String[][] getCoberturas() {
        return coberturas;
    }

}
