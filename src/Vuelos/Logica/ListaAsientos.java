package Vuelos.Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaAsientos {

    private List<Asiento> asientos;
    private int contador;

    public ListaAsientos() {
        asientos = new ArrayList<>();
    }

    public ListaAsientos(List<Asiento> asientos, int contador) {
        this.asientos = asientos;
        this.contador = contador;
    }

    public void añadir(Asiento a) {
        this.asientos.add(a);
    }

    public void eliminar(int num) {
        Iterator<Asiento> iterador = asientos.iterator();
        while (iterador.hasNext()) {
            Asiento a = iterador.next();
            a.CancelarReservaAsiento();
            if (a.getNumero() == num) {
                iterador.remove();
                break;
            }
        }
    }
    public List<Asiento> getAsientos(){
        return asientos;
    }

}
