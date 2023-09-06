package Tours;

import Reservas.Reserva;
import Reservas.ReservaTour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorReserva {

    private ArrayList<Reserva> reservaciones;
    private GestorTour gestorTour;
    private PagoReserva pagoReserva;

    public GestorReserva() {
        this.reservaciones = new ArrayList<>();
        this.leerDatos();
    }

    private void leerDatos(){
        ArrayList<Tour> toursAgregados = new ArrayList<>();
        ReservaTour reservaTour;
        Tour tour;
        int i;

        try {
            BufferedReader br = new BufferedReader(new FileReader("reservas.txt"));

            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int tamanio = datos.length;

                //Para leer en caso de que haya más de 1 tour
                for (i = 4; i < tamanio; i++) {
                    tour = leerTours(datos[i]);
                    toursAgregados.add(tour);
                }

                String fechaCreacion = datos[2];

                reservaTour = new ReservaTour(datos[0], datos[1], Integer.parseInt(datos[3]),gestorTour, pagoReserva);
                reservaTour.setFechaCreacion(fechaCreacion);
                reservaTour.setToursAgregados(toursAgregados);

                this.reservaciones.add(reservaTour);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Tour leerTours(String datos) {
        Tour tourResultado;
        ArrayList<String> paradasList = new ArrayList<>();
        ArrayList<String> actividadesList = new ArrayList<>();
        Tour tour;
        String linea = "";
        String[] datosALeer = datos.split(",");

        String[] paradas = datosALeer[2].replace('[', ' ').replace(']', ' ').trim().split(";");
        String[] actividades = datosALeer[3].replace('[', ' ').replace(']', ' ').trim().split(";");
        String fechaInicio = datosALeer[7];
        String fechaFin =  datosALeer[datosALeer.length-1];

        Collections.addAll(paradasList, paradas);

        Collections.addAll(actividadesList, actividades);

        tourResultado = new Tour(datosALeer[0] , Double.parseDouble(datosALeer[1]),paradasList, actividadesList, datosALeer[4], datosALeer[5],Integer.parseInt(datosALeer[6]), fechaInicio, fechaFin);
        return tourResultado;
    }

    public void removerReserva(Reserva reservaARemover){
        Reserva reservaACancelar = null;
        for (Reserva reserva : this.reservaciones) {
            if (reserva.equals(reservaARemover)) {
                this.reservaciones.remove(reservaACancelar);
                break;
            }
        }
    }
    public void agregarReserva(Reserva reservaAAgregar) {
        this.reservaciones.add(reservaAAgregar);
    }

}