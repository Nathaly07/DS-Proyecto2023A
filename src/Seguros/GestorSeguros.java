package Seguros;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class GestorSeguros {
    private ArrayList<Seguro> Seguros = new ArrayList<Seguro>();

    public void GestorSeguros() {

    }

    public void eliminarSeguro(Seguro seguroEliminar) {
        if (seguroEliminar != null) {
            this.Seguros.remove(seguroEliminar);
            JOptionPane.showMessageDialog(null, "Seguro eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "Seguro no eliminado");
        }
    }


    public ArrayList<Seguro> buscarSeguro (String propietario){
        ArrayList<Seguro> SeguroPropietarios = new ArrayList<>();
        for (Seguro seguro : Seguros) {
            if (seguro.getPropietario().equalsIgnoreCase(propietario)) {
                SeguroPropietarios.add(seguro);
            }
        }
        return SeguroPropietarios;
    }

    public void procesarCreaciónSeguro (Seguro seguro){
        this.Seguros.add(seguro);
        JOptionPane.showMessageDialog(null, "Se ha creado el seguro con éxito.");
    }


}