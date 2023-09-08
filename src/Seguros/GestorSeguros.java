package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class GestorSeguros {
    private ArrayList<Seguro> Seguros = new ArrayList();

    public GestorSeguros() {
    }

    public void GestorSeguros() {
    }

    public void eliminarSeguro(Seguro seguroEliminar) {
        if (seguroEliminar != null) {
            this.Seguros.remove(seguroEliminar);
            JOptionPane.showMessageDialog((Component)null, "Seguro eliminado");
        } else {
            JOptionPane.showMessageDialog((Component)null, "Seguro no eliminado");
        }

    }

    public ArrayList<Seguro> buscarSegurosCliente(Usuario cliente) {
        ArrayList<Seguro> segurosCliente = new ArrayList();
        Iterator var3 = this.Seguros.iterator();

        while(var3.hasNext()) {
            Seguro seguro = (Seguro)var3.next();
            if (cliente.getNombre().equalsIgnoreCase(seguro.getPropietario().getNombre())) {
                segurosCliente.add(seguro);
            }
        }

        return segurosCliente;
    }

    public void procesarCreaciónSeguro(Seguro seguro) {
        this.Seguros.add(seguro);
        JOptionPane.showMessageDialog((Component)null, "Se ha creado el seguro con éxito.");
    }
}
