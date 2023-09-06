package Tours;

import Reservas.Reserva;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorReserva {

    private ArrayList<Reserva> reservaciones;

    public GestorReserva() {
        this.reservaciones = new ArrayList<>();
        this.leerDatos();
    }

    private void leerDatos(){
        ArrayList<String> paradasList = new ArrayList<>();
        ArrayList<String> actividadesList = new ArrayList<>();
        Tour tour;

        try {
            BufferedReader br = new BufferedReader(new FileReader("reservas.txt"));

            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String[] paradas = datos[3].replace('[', ' ').replace(']', ' ').trim().split(";");
                String[] actividades = datos[4].replace('[', ' ').replace(']', ' ').trim().split(";");
                String fechaInicio = datos[8];
                String fechaFin =  datos[datos.length-1];

                Collections.addAll(paradasList, paradas);

                Collections.addAll(actividadesList, actividades);

                tour = new Tour(datos[1] , Double.parseDouble(datos[2]),paradasList, actividadesList, datos[5], datos[6],Integer.parseInt(datos[7]), fechaInicio, fechaFin);
                this.reservaciones.add(tour);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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

    public Reserva buscarReserva(String idReserva){
        for (Reserva reserva: this.reservaciones){
            if(reserva.getReservaID().equalsIgnoreCase(idReserva)) {
                return reserva;
            }
        }
        return null;
    }

}