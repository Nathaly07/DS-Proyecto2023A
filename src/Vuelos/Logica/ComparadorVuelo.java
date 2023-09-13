package Vuelos.Logica;

import java.util.Comparator;

public class ComparadorVuelo implements Comparator<Vuelo> {
    @Override
    public int compare(Vuelo o1, Vuelo o2) {
        if (o1.getOrigen().equalsIgnoreCase(o2.getOrigen()) &&
                o1.getDestino().equalsIgnoreCase(o2.getDestino()) &&
                o1.getFecha().equalsIgnoreCase(o2.getFecha()) &&
                o1.getHoraSalida().equalsIgnoreCase(o2.getHoraSalida())){
            return 1;
        }
        return 0;
    }
}
