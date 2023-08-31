package Vuelos.Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoAsientos {

    private List<Asiento> asientos;
    private Vuelo vuelo;

    public CarritoAsientos() {
        asientos = new ArrayList<>();
    }

    public CarritoAsientos(List<Asiento> asientos, Vuelo vuelo) {
        this.asientos = asientos;
        this.vuelo = vuelo;
    }

    public void añadir(Asiento a) {
        this.asientos.add(a);
    }

    public void eliminar(Asiento a) {
        Iterator<Asiento> iterador = asientos.iterator();
        while (iterador.hasNext()) {
            Asiento i = iterador.next();
            i.CancelarReservaAsiento();
            if (i.getNumero() == a.getNumero()) {
                iterador.remove();
                break;
            }
        }
    }
    public List<Asiento> getAsientos(){
        return asientos;
    }

}
