package Seguros;

import java.util.ArrayList;

public class GestorSeguros {
    private ArrayList<Seguro> Seguros = new ArrayList<Seguro>();

    public void GestorSeguros() {

    }

    public void agregarSeguro(Seguro seguro) {
        this.Seguros.add(seguro);
    }

    public boolean eliminarSeguro(int ID_Seguro) {
        Seguro seguroEliminar = buscarSeguro(ID_Seguro);
        if (seguroEliminar != null) {
            this.Seguros.remove(seguroEliminar);
            return true;
        } else {
            return false;
        }
    }

    public Seguro buscarSeguro(int ID_Seguro) {
        Seguro seguroBuscar = null;
        for (Seguro seguro : Seguros) {
            if (seguro.getID_Seguro() == ID_Seguro) {
                seguroBuscar = seguro;
                break;
            }
        }
        return seguroBuscar;
    }
}
