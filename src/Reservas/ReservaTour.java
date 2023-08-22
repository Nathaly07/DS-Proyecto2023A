package Reservas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.text.SimpleDateFormat;

public class ReservaTour extends Reserva{
    private boolean activado;
    private int numeroPersonas;
    private boolean seguroActivado;
    private Date fechaCreacion;
    private Date fechaConfirmacion;
    private ArrayList<Tour> listaTours;
    private GestionTour gestionTour;

    private SimpleDateFormat format = new SimpleDateFormat("dd/M/yy");

    public ReservaTour(String usuarioID, GestionTour gestionTour, Date fechaCreacion, Date fechaConfirmacion, int numeroPersonas, boolean seguroActivado, ArrayList<Tour> listaTours) {
        super(usuarioID, reservaID);
        this.listaTours = new ArrayList<>();
        this.gestionTour = gestionTour;
        this.fechaConfirmacion = fechaConfirmacion;
        this.fechaCreacion = fechaCreacion;
        this.activado = true;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public void agregarTour(Tour tour){
        this.listaTours.add(tour);
    }

    public void eliminarTour(Tour tour){
        this.listaTours.remove(tour);
    }

    public String buscarTour(String nombreTour){
        return this.gestionTour.getTour(nombreTour);
    }

    public int tiempoSinConfirmar(){
        LocalDate fecha = LocalDate.now();
        Date fechaActual = format.parse(fecha.toString());
        int milisecondsByDay = 86400000;
        int dias = (int) ((fechaActual.getTime()-fechaCreacion.getTime()) / milisecondsByDay);
        return dias;
    }

    public int tiempoTrasCancelar(){
        LocalDate fecha = LocalDate.now();
        Date fechaActual = format.parse(fecha.toString());
        int milisecondsByDay = 86400000;
        int dias = (int) ((fechaActual.getTime()-fechaConfirmacion.getTime()) / milisecondsByDay);
        return dias;
    }


    @Override
    public void cancelarReserva() {
        if (this.activado = true){
            this.activado = false;
        }else {
            JOptionPane.showMessageDialog(null, 
                                            "La reserva ya ha sido cancelada", 
                                            "Reserva Tour", 
                                            JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void modificarReserva() {
        
    }
    
}
