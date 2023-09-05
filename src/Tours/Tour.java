package Tours;

import Reservas.ReservaTour;

import java.util.ArrayList;
import java.util.Date;

public class Tour {
    private String nombre;
    private double precio;
    private ArrayList <String> paradasTuristicas;
    private ArrayList <String> actividadesTuristicas;
    private String info_guia;
    private String duracion;
    private int limite_usuarios;
    private int disponibilidad;
    private String fechaInicio;
    private String fechaFin;



    //Constructor

    public Tour(String nombre, double precio, ArrayList<String> paradasTuristicas, ArrayList<String> actividadesTuristicas, String info_guia, String duracion, int limite_usuarios,int disponibilidad, String fechaInicio, String fechaFin) {
        this.nombre = nombre;
        this.precio = precio;
        this.paradasTuristicas = paradasTuristicas;
        this.actividadesTuristicas = actividadesTuristicas;
        this.info_guia = info_guia;
        this.duracion = duracion;
        this.limite_usuarios = limite_usuarios;
        this.disponibilidad = disponibilidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public double getPrecio() {
        return precio;
    }

    public int reservarTour(int numPersonas){
        return getDisponibilidad()-numPersonas;
    }

    public int cancelarReserva(int numPersonas){
        return getDisponibilidad()+numPersonas;
    }

    public int getLimite_usuarios() {
        return limite_usuarios;
    }

    //public int getDisponibilidad (){
     //   int result = 0;
     //   int contReservas = 0;
//<<<<<<< HEAD
     //   for (ReservaTour reserva : this.gestion_reserva.getReservas()) {
     //       contReservas += this.gestion_reserva.totalPersonasPorTour(this.nom);
       // }
//=======
        /*for (ReservaTour reserva : this.gestion_reserva.reservaciones) {
            contReservas += reserva.getNumeroPersonas();
        }*/
//>>>>>>> 2bfb696a5fa4c4b8f965b0620b53a7803c090156
       // return result = this.limite_usuarios - contReservas;
  //  }
    public int getDisponibilidad(){
        return this.disponibilidad;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }

    //ToString para mostrar en la informacion
    public String informacionRelevante(){
        String info = "";

        info += "Tour: " + this.nombre + "\n";
        info += "Paradas: \n";

        int contador = 0;
        for (String parada : paradasTuristicas) {
            info += parada + ",\n";
            contador++;
            if (contador >= 2) {
                break;
            }
        }

        info += "Guia: " + this.info_guia + "\n";
        info += "Duracion: " + this.duracion + "\n";
        info += "Precio: $" + this.precio;

        return info;
    }

    //Método para agregar parada turistica

    public void agregarParadaTuristica(String paradaTurisitica) {
        this.paradasTuristicas.add(paradaTurisitica);
    }

    //Metodo para eliminar parada turistica

    public void eliminarParadaTuristica(String paradaTuristica) {
        this.paradasTuristicas.remove(paradaTuristica);
    }

    //Metodo para agregar actividad turistica

    public void agregarActividadTuristica(String actividadTuristica){
        this.actividadesTuristicas.add(actividadTuristica);
    }

    //Metodo para eliminar actividad turistica

    public void eliminarActividadTuristica(String actividadTuristica){
        this.actividadesTuristicas.remove(actividadTuristica);
    }

}
