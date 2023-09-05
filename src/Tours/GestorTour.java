package Tours;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
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
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Tour tour;

        try {
            BufferedReader br = new BufferedReader(new FileReader("tours.txt"));

            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                String[] paradas = datos[3].replace('[', ' ').replace(']', ' ').trim().split(";");
                String[] actividades = datos[4].replace('[', ' ').replace(']', ' ').trim().split(";");
                Date fechaInicio = formato.parse(datos[8]);
                Date fechaFin = formato.parse(datos[datos.length-1]);

                for(int i = 0; i < paradas.length; i++){
                    paradasList.add(paradas[i]);
                }

                for(int i = 0; i < actividades.length; i++){
                    actividadesList.add(actividades[i]);
                }

                tour = new Tour(datos[1] , Double.parseDouble(datos[2]),paradasList, actividadesList, datos[5], datos[6],Integer.parseInt(datos[7]), fechaInicio, fechaFin);
                this.tours.add(tour);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
