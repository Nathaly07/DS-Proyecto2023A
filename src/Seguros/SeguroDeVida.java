package Seguros;

import java.util.Date;

public class SeguroDeVida extends Seguro {
    private float montoAPagar;
    private int nivelSeguro;

    public SeguroDeVida(int ID_Seguro, String propietario, String[] condiciones, String[] beneficiarios, Date fechaDeInicio, Date fechaDeVencimiento, float primaSinRecargo, float montoAPagar) {
        super(ID_Seguro, propietario, condiciones, beneficiarios, fechaDeInicio, fechaDeVencimiento, primaSinRecargo);
        this.montoAPagar = montoAPagar;
    }

    @Override
    float indemnizar(float valorPedido, String motivo) {
        float totalMonto = 0;
        if (valorPedido <= 400000 && motivo.equalsIgnoreCase("muerte")) {
            if (nivelSeguro == 1) {
                totalMonto = 100000;
            } else if (nivelSeguro == 2) {
                totalMonto = 200000;
            } else if (nivelSeguro == 3) {
                totalMonto = 300000;
            } else if (nivelSeguro == 4) {
                totalMonto = 400000;
            }
        }
        return totalMonto;
    }

    @Override
    float calcularPrimaTotal() {
        float total = 0;
        if (nivelSeguro == 1) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.4) * getBeneficiarios().length;
        } else if (nivelSeguro == 2) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.45) * getBeneficiarios().length;
        } else if (nivelSeguro == 3) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.5) * getBeneficiarios().length;
        } else if (nivelSeguro == 4) {
            total = (float) (this.getPrimaSinRecargo() + this.getPrimaSinRecargo() * 0.6) * getBeneficiarios().length;
        }
        return total;
    }
}
