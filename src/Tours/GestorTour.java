package Tours;

import java.util.ArrayList;
import java.util.List;
public class GestorTour {
    private List<Tour> tours;

    public GestorTour() {
        this.tours = new ArrayList<>();

        //this.tours.add(new Tour("T001", "Ruta del", 10));
    }

    // Obtener los tours disponibles
    public List<Tour> getToursDisponibles() {
        List<Tour> toursDisponibles = new ArrayList<>();
        for (Tour tour : this.tours) {
            if (tour.getDisponibilidad() > 0) {
                toursDisponibles.add(tour);
            }
        }
        return toursDisponibles;
    }

    //Buscar Tour por nombre
    public Tour buscarTour(String nombreTour) {
        for (Tour tour : this.tours) {
            if (tour.getNombre().equals(nombreTour)) {
                return tour;
            }
        }
        return null;
    }

    //Obtener lista de Tours
    public List<Tour> getTours() {
        return this.tours;
    }

}
