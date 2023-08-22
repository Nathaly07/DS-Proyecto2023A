package Tours;

import java.util.ArrayList;
import java.util.List;
public class Gestion_Tour {
    private List<Tour> tours;
    private List<Parada> paradas;

    public Gestion_Tour() {
        this.tours = new ArrayList<>();
        this.paradas = new ArrayList<>();
    }
// Metodo para retorna el tour

    public Tour getTour(String nombre) {
        for (Tour tour : this.tours) {
            if (tour.getTourID().equalsIgnoreCase(nombre)) {
                return tour;
            }
        }
        return null;
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

//Agregar Tours.Tour

    public void nuevoTour(Tour tour) {
        this.tours.add(tour);
    }
//Actualizar Tours.Tour

    public void actualizarTour(String id, Tour changes) {
        for (Tour tour : this.tours) {
            if (tour.getTourID().equalsIgnoreCase(id)) {
                // Actualizar los atributos necesarios según los cambios
                tour.setNombre(changes.getNombre());
                tour.setPrecio(changes.getPrecio());
                break;
            }
        }
    }
//Eliminar Tours.Tour

    public void eliminarTour(String id) {
        Tour tourAEliminar = null;
        for (Tour tour : this.tours) {
            if (tour.getTourID().equalsIgnoreCase(id)) {
                tourAEliminar = tour;
                break;
            }
        }
        if (tourAEliminar != null) {
            this.tours.remove(tourAEliminar);
        }
    }
//Promocionar Tours.Tour

    public String promocionarTour(String nombre) {
        Tour tourPromocionado = getTour(nombre);

        if (tourPromocionado != null) {
            String promocion = "¡Promoción de Tours.Tour!\n";
            promocion += "Nombre: " + tourPromocionado.getNombre() + "\n";
            promocion += "Precio: $" + tourPromocionado.getPrecio() + "\n";
            promocion += "Duración: " + tourPromocionado.getDuracion() + " horas\n";

            return promocion;
        } else {
            return "Tours.Tour no encontrado.";
        }
    }

    // Agregar una nueva parada
    public void nuevaParada(Parada parada) {
        this.paradas.add(parada);
    }

    //Eliminar Tours.Parada
    public void eliminarParada(int id) {
        Parada paradaAEliminar = null;
        for (Parada parada : this.paradas) {
            if (parada.getParadaId() == id) {
                paradaAEliminar = parada;
                break;
            }
        }
        if (paradaAEliminar != null) {
            paradas.remove(paradaAEliminar);
        }
    }

    //Obtener lista de Tours
    public List<Tour> getTours() {
        return this.tours;
    }

    // Obtener la lista de paradas
    public List<Parada> getParadas() {
        return this.paradas;
    }
}
