package Seguros;

import javax.swing.*;
import java.util.Date;

public class ModuloSeguros {
    private GestorSeguros seguros = new GestorSeguros();

    public void ModuloSeguros() {

    }

    public void procesarCreacionSeguro(Seguro seguro) {
        this.seguros.agregarSeguro(seguro);
        JOptionPane.showMessageDialog(null, "Se ha creado el seguro con éxito.");
    }

    public void procesarRenovarSeguro(int ID_Seguro, Date fecha) {
        if (this.seguros.buscarSeguro(ID_Seguro).renovar(fecha)) {
            JOptionPane.showMessageDialog(null, "Se ha renovado el seguro: " + ID_Seguro + " con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha renovado el seguro.");
        }
        ;
    }

    public void procesarCancelarSeguro(int ID_Seguro) {
        Seguro seguroEliminar = this.seguros.buscarSeguro(ID_Seguro);
        if (seguroEliminar == null) {
            JOptionPane.showMessageDialog(null, "Ese seguro no existe.");
        } else {
            this.seguros.buscarSeguro(ID_Seguro).cancelar(ID_Seguro);
            this.seguros.eliminarSeguro(ID_Seguro);
            JOptionPane.showMessageDialog(null, "Seguro eliminado con éxito.");
        }

    }

    public void procesarPagoSeguro(int ID_Seguro, Date fechaPago) {
        Seguro seguroCobrar = this.seguros.buscarSeguro(ID_Seguro);
        if (seguroCobrar == null) {
            JOptionPane.showMessageDialog(null, "Ese seguro no existe.");
        } else {
            PagoSeguros pagoSeguros = new PagoSeguros(seguroCobrar, fechaPago);
            pagoSeguros.cobrarSeguro();
        }
    }

    public void procesarIndemnizacion(int ID_Seguro, String motivo, float montoPedido) {
        float montoARetornar = 0.0f;
        Seguro seguroIndemnizar = this.seguros.buscarSeguro(ID_Seguro);
        if (seguroIndemnizar == null) {
            JOptionPane.showMessageDialog(null, "Ese seguro no existe.");
        } else {
            montoARetornar = this.seguros.buscarSeguro(ID_Seguro).indemnizar(montoPedido, motivo);
            JOptionPane.showMessageDialog(null, "Te daremos: " + montoARetornar);
        }
    }
}
