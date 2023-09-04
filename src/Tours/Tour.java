package Tours;

import Reservas.ReservaTour;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Tour {

    private String tourID;
    private String nombre;
    private double precio;
    private ArrayList <Parada> paradasTuristicas;
    private String info_guia;
    private String duracion;
    private int limite_usuarios;
    private GestionReserva gestion_reserva;


    //Constructor

    public Tour(String tourID, String nombre, double precio, String info_guia, String duracion, int limite_usuarios, GestionReserva gestion_reserva) {
        this.tourID = tourID;
        this.nombre = nombre;
        this.precio = precio;
        this.paradasTuristicas = null;
        this.info_guia = info_guia;
        this.duracion = duracion;
        this.limite_usuarios = limite_usuarios;
        this.gestion_reserva = gestion_reserva;
    }

    //Getters y Setters

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Parada> getParadasTuristicas() {
        return paradasTuristicas;
    }

    public void setParadasTuristicas(ArrayList<Parada> paradasTuristicas) {
        this.paradasTuristicas = paradasTuristicas;
    }

    public String getInfo_guia() {
        return info_guia;
    }

    public void setInfo_guia(String info_guia) {
        this.info_guia = info_guia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getLimite_usuarios() {
        return limite_usuarios;
    }

    public void setLimite_usuarios(int limite_usuarios) {
        this.limite_usuarios = limite_usuarios;
    }

    public void agregarParada(Parada parada) {
        this.paradasTuristicas.add(parada);
    }

    public void eliminarParada(int paradaId) {
        Parada paradaAEliminar = null;
        for (Parada parada : this.paradasTuristicas) {
            if (parada.getParadaId().equals(paradaId)) {
                paradaAEliminar = parada;
                break;
            }
        }
        if (paradaAEliminar != null) {
            paradasTuristicas.remove(paradaAEliminar);
            JOptionPane.showMessageDialog(null,
                    "La parada se ha eliminado correctamente.",
                    "Tour",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "La parada no se ha eliminado porque no existe en la lista.",
                    "Tour",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public int getDisponibilidad (){
        int result = 0;
        int contReservas = 0;
//<<<<<<< HEAD
        for (ReservaTour reserva : this.gestion_reserva.getReservas()) {
            contReservas += this.gestion_reserva.totalPersonasPorTour(this.tourID);
        }
//=======
        /*for (ReservaTour reserva : this.gestion_reserva.reservaciones) {
            contReservas += reserva.getNumeroPersonas();
        }*/
//>>>>>>> 2bfb696a5fa4c4b8f965b0620b53a7803c090156
        return result = this.limite_usuarios - contReservas;
    }

    //ToString para mostrar en la informacion
    public String toString(){
        String info = "";

        info += "Tour: " + this.nombre;
        info +=  "\nParadas: \n";

        for (Parada x: paradasTuristicas) {
            info += x.getDestino() + ", \n ";
        }

        info += "Guia: $" + this.info_guia;
        info += "Duracion: $" + this.duracion;
        info += "Precio: $" + this.precio;

        return info;
    }

}
