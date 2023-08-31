package Vuelos.Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorReservasAsiento {
    private List<ReservaAsiento> reservas;

    public GestorReservasAsiento(){
        reservas = new ArrayList<>();
    }
    public void AgregarResarva(ReservaAsiento r){
        reservas.add(r);
    }

public void QuiteReserva(ReservaAsiento r){
    Iterator<ReservaAsiento> iterador = reservas.iterator();
    while (iterador.hasNext()) {
        ReservaAsiento i = iterador.next();
        if (i.equals(r)) {
            iterador.remove();
            break;
        }
    }
}
}
