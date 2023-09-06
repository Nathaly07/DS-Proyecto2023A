package Tours;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class GestorTour {
    private List<Tour> tours;

    public GestorTour() {
        this.tours = new ArrayList<>();
        this.leerDatos();
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

    private void leerDatos(){
        ArrayList<String> paradasList = new ArrayList<>();
        ArrayList<String> actividadesList = new ArrayList<>();
        Tour tour;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Tours/datos/tours.txt"));

            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                String[] paradas = datos[2].replace('[', ' ').replace(']', ' ').trim().split(";");
                String[] actividades = datos[3].replace('[', ' ').replace(']', ' ').trim().split(";");
                String fechaInicio = datos[7];
                String fechaFin =  datos[datos.length-1];

                Collections.addAll(paradasList, paradas);

                Collections.addAll(actividadesList, actividades);

                tour = new Tour(datos[0] , Double.parseDouble(datos[1]),paradasList, actividadesList, datos[4], datos[5],Integer.parseInt(datos[6]), fechaInicio, fechaFin);
                this.tours.add(tour);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
