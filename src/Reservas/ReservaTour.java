package Reservas;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import Tours.Tour;
import Tours.Gestion_Tour;


public class ReservaTour extends Reserva{
    private boolean activado;
    private int numeroPersonas;
    private boolean seguroActivado;
    private Date fechaCreacion;
    private Date fechaConfirmacion;
    private ArrayList<Tour> toursAgregados;
    private Gestion_Tour gestionTour;

    private SimpleDateFormat format = new SimpleDateFormat("dd/M/yy");

    public ReservaTour(String usuarioID, String reservaID, Gestion_Tour gestionTour, Date fechaCreacion, int numeroPersonas, boolean seguroActivado, ArrayList<Tour> toursAgregados) {
        super(usuarioID, reservaID);
        this.toursAgregados = new ArrayList<Tour>();
        this.gestionTour = gestionTour;
        this.fechaCreacion = fechaCreacion;
        this.activado = true;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public void setActivado(boolean activado) { this.activado = activado; }

    public void setSeguroActivado(boolean seguroActivado) { this.seguroActivado = seguroActivado; }

    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public void setFechaConfirmacion(Date fechaConfirmacion) { this.fechaConfirmacion = fechaConfirmacion; }

    public void setToursAgregados(ArrayList<Tour> toursAgregados) { this.toursAgregados = toursAgregados; }

    public void setGestionTour(Gestion_Tour gestionTour) { this.gestionTour = gestionTour; }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public boolean isActivado() { return activado; }

    public boolean isSeguroActivado() { return seguroActivado; }

    public Date getFechaCreacion() { return fechaCreacion; }

    public Date getFechaConfirmacion() { return fechaConfirmacion; }

    public Gestion_Tour getGestionTour() { return gestionTour; }

    public void agregarTour(Tour tour){
        this.toursAgregados.add(tour);
    }

    public void eliminarTour(String nombreTour){
        this.toursAgregados.remove(this.buscarTour(nombreTour));
    }

    public Tour buscarTour(String nombreTour){
        return this.gestionTour.getTour(nombreTour);
    }

    public int tiempoSinConfirmar(){
        LocalDate fecha = LocalDate.now();
        Date fechaActual = null;
        try {
            fechaActual = format.parse(fecha.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int milisecondsByDay = 86400000;
        int dias = 10;
        return dias;
    }

    public int tiempoTrasCancelar(){
        LocalDate fecha = LocalDate.now();
        Date fechaActual = null;
        try {
            fechaActual = format.parse(fecha.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int milisecondsByDay = 86400000;
        int dias = 10;
        return dias;
    }

    public ArrayList<Tour> getToursAgregados() {
        return toursAgregados;
    }
    
    @Override
    public void cancelarReserva() {
        if (this.activado = true){
            this.activado = false;
        }else {
            JOptionPane.showMessageDialog(null, 
                                            "La reserva ya ha sido cancelada", 
                                            "Reserva Tours.Tour",
                                            JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void modificarReserva() {
        
    }
    
}
