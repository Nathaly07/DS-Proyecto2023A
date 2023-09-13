package Tours;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class GestorReserva {

    private ArrayList<ReservaTour> reservaciones;
    private GestorTour gestorTour;
    private PagoReserva pagoReserva;

    public GestorReserva() {
        this.reservaciones = new ArrayList<>();
        this.leerDatos();
    }

    public ArrayList<ReservaTour> getReservaciones() {
        return reservaciones;
    }

    private void leerDatos(){
        ArrayList<Tour> toursAgregados;
        ReservaTour reservaTour;
        Tour tour;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Tours/datos/reservas.txt"));

            String linea = "";
            while ((linea = br.readLine()) != null) {
                toursAgregados = new ArrayList<>();
                String[] datos = linea.split("_");
                int tamanio = datos.length;

                //Para leer en caso de que haya más de 1 tour
                for (int i = 4; i < tamanio; i++) {
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

    public void removerReserva(ReservaTour reservaARemover){
        for (ReservaTour reserva : this.reservaciones) {
            if (reserva.equals(reservaARemover)) {
                this.reservaciones.remove(reserva);
                break;
            }
        }
    }

    public void agregarReserva(ReservaTour reservaAAgregar) {
        this.reservaciones.add(reservaAAgregar);
        JOptionPane.showMessageDialog(null, "Reserva Agregada");
        guardarReserva(reservaAAgregar);
    }

    public void guardarReserva(ReservaTour reserva) {
        String datosReserva = "";
        try (FileWriter escritor = new FileWriter("src/Tours/datos/reservas.txt", true)) {
            int contTours = 0;
            int contParadas = 0;
            int contActividades = 0;
            escritor.write(System.lineSeparator()); // Añade un salto de línea al final del archivo
            datosReserva += reserva.getNombreUsuario() + "_";
            datosReserva += reserva.getApellidoUsuario() + "_";
            datosReserva += reserva.getFechaCreacion() + "_";
            datosReserva += reserva.getNumeroPersonas() + "_";
            for (Tour tour : reserva.getToursAgregados()) {
                contTours++;
                datosReserva += "[" + tour.getNombre() + ",";
                datosReserva += tour.getPrecio() + ",[";

                for (String parada : tour.getParadasTuristicas()) {
                    contParadas++;
                    datosReserva += parada;
                    if (contParadas != tour.getParadasTuristicas().size()) {
                        datosReserva += ";";
                    }
                }

                datosReserva += "],[";

                for (String actividad : tour.getActividadesTuristicas()) {
                    contActividades++;
                    datosReserva += actividad;
                    if (contActividades != tour.getActividadesTuristicas().size()) {
                        datosReserva += ";";
                    }
                }

                datosReserva += "],";
                datosReserva += tour.getInfoGuia() + ",";
                datosReserva += tour.getDuracion() + ",";
                datosReserva += tour.getLimiteUsuarios() + ",";
                datosReserva += tour.getFechaInicio()+ ",";
                datosReserva += tour.getFechaFin();

                if (contTours != reserva.getToursAgregados().size()) {
                    datosReserva += "_";
                }
            }
            escritor.write(datosReserva);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void confirmarReserva(ReservaTour reservaAPagar, String metodoPago) throws ParseException {
        if (reservaAPagar.equals(null)) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una reserva por favor",
                    "Gestor Reserva",
                    JOptionPane.WARNING_MESSAGE);
        } else if(metodoPago.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null,
                    "Seleccione un método de pago por favor",
                    "Gestor Reserva",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            if (reservaAPagar.tiempoSinConfirmar() > 10) {
                JOptionPane.showMessageDialog(null,
                        "La reserva ha excedido el número de días sin confirmar.\n" +
                                "Por tanto, se cancela automáticamente la reserva.\n" +
                                "No es posible pagar la reserva.",
                        "Gestor Reserva",
                        JOptionPane.WARNING_MESSAGE);
                reservaAPagar.cancelarReserva();
                this.removerReserva(reservaAPagar);
            } else {
                reservaAPagar.confirmarReserva(metodoPago);
            }
        }

    }

    public ReservaTour buscarReserva(int numReserva) {
        ReservaTour reservaResultado = null;
        for (ReservaTour reserva : this.reservaciones) {
            if (reserva.getNumReserva() == numReserva) {
                reservaResultado = reserva;
            }
        }
        if (reservaResultado.equals(null)) {
            JOptionPane.showMessageDialog(null,
                    "No existe la reserva que se busca.",
                    "Gestor Reserva",
                    JOptionPane.WARNING_MESSAGE);
        }
        return reservaResultado;
    }

    public ArrayList<String> getRangosFechas(String nombreUsuario, String apellidoUsuario) throws ParseException {
        ArrayList<String> rangosFechas = new ArrayList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        ArrayList<Date> tempFechas = new ArrayList<Date>();
        long minTime = Long.MAX_VALUE;
        long maxTime = 0;
        Date minFecha = new Date();
        Date maxFecha = new Date();
        String rango = "";

        for(ReservaTour reserva: this.reservaciones) {
            if(reserva.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && reserva.getApellidoUsuario().equalsIgnoreCase(apellidoUsuario)){
                for(Tour tour: reserva.getToursAgregados()) {
                    tempFechas.add(dateFormat.parse(tour.getFechaInicio()));
                    tempFechas.add(dateFormat.parse(tour.getFechaFin()));
                }
                for(Date fecha: tempFechas) {
                    if(fecha.getTime() < minTime) { minTime = fecha.getTime(); minFecha = fecha; }
                    if(fecha.getTime() > maxTime) { maxTime = fecha.getTime(); maxFecha = fecha; }
                }
                rango = dateFormat.format(minFecha) + "-" + dateFormat.format(maxFecha);
                rangosFechas.add(rango);
                minTime = Long.MAX_VALUE;
                maxTime = 0;
            }
        }

        return rangosFechas;
    }

}