package Tours;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

public class GestorTour {
    private List<Tour> tours;

    public GestorTour() {
        this.tours = new ArrayList<>();
        this.leerDatos();
    }

    // Obtener los tours disponibles
    public List<Tour> getToursDisponibles(String destino, Date fechaTentativa) {
        List<Tour> toursDisponibles = new ArrayList<>();
        List<Tour> toursDestino = getToursDestino(destino);
        List<Tour> toursFecha  = getToursFecha(fechaTentativa);

        List<Tour> toursFinal = new ArrayList<>();

        if(toursDestino == null && toursFecha == null){
            toursFinal = this.tours;
        }else{
            toursFinal = toursDestino.stream().filter(f-> toursFecha.contains(f)).toList();
        }

        for (Tour tour : toursFinal) {
            if (tour.getDisponibilidad() > 0 ) {
                toursDisponibles.add(tour);
            }
        }
        return toursDisponibles;
    }

    //Filtrar tour por fechas
    public List<Tour> getToursFecha(Date fechaTentativa){
        if(fechaTentativa == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<Tour> toursFinal = new ArrayList<>();

        for(Tour tour: this.tours){
            try {
                Date fechaTour = format.parse(tour.getFechaInicio());
                Date fechaT = format.parse(format.format(fechaTentativa));
                if(fechaTour.after(fechaT)){
                    toursFinal.add(tour);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if(toursFinal.isEmpty()){
            return null;
        }
        return toursFinal;
    }

    //Buscar Tour por nombre
    public List<Tour> getToursDestino(String destino) {

        if(destino == null){
            return null;
        }

        List<Tour> toursDestino = new ArrayList<>();
        for (Tour tour : this.tours) {
            if (tour.getNombre().contains(destino)) {
                toursDestino.add(tour);
            }
        }

        if(toursDestino.isEmpty()){
            return null;
        }
        return toursDestino;
    }

    public Tour buscarTour(String nombre) {

        for (Tour tour : this.tours) {
            if (tour.getNombre().contains(nombre)) {
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

        Tour tour;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Tours/datos/tours.txt"));

            String linea = "";
            while ((linea = br.readLine()) != null) {
                ArrayList<String> paradasList = new ArrayList<>();
                ArrayList<String> actividadesList = new ArrayList<>();

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
