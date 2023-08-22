package Reservas;

import java.util.ArrayList;

public class ReservaTour extends Reserva{
    private int numeroPersonas;
    private boolean seguroActivado;
    private String fechaCreacion;
    private String fechaConfirmacion;
    private ArrayList<Tour> listaTours;
    private GestionTour gestionTour;


    public ReservaTour(String usuarioID, GestionTour gestionTour) {
        super(usuarioID, reservaID);
        this.listaTours = new ArrayList<>();
        this.gestionTour = 
    }

    public void agregarTour(Tour tour){
        this.listaTours.add(tour);
    }

    public void eliminarTour(Tour tour){
        this.listaTours.remove(tour);
    }

    public void buscarTour(Tour tour){

    }

    @Override
    public void cancelarReserva() {
        
    }

    @Override
    public void modificarReserva() {
        
    }
    
}
