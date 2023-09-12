package Tours;

import java.util.ArrayList;

public class Tour {
    private String nombre;
    private double precio;
    private ArrayList <String> paradasTuristicas;
    private ArrayList <String> actividadesTuristicas;
    private String infoGuia;
    private String duracion;
    private int limiteUsuarios;
    private int disponibilidad;
    private String fechaInicio;
    private String fechaFin;


    //Constructor

    public Tour(String nombre, double precio, ArrayList<String> paradasTuristicas, ArrayList<String> actividadesTuristicas, String infoGuia, String duracion, int limiteUsuarios, String fechaInicio, String fechaFin) {
        this.nombre = nombre;
        this.precio = precio;
        this.paradasTuristicas = paradasTuristicas;
        this.actividadesTuristicas = actividadesTuristicas;
        this.infoGuia = infoGuia;
        this.duracion = duracion;
        this.limiteUsuarios = limiteUsuarios;
        this.disponibilidad = limiteUsuarios;
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

    public void reservarTour(int numPersonas){
        this.disponibilidad -= numPersonas;
    }

    public void cancelarReservaTour(int numPersonas){
        this.disponibilidad += numPersonas;
    }

    public int getLimiteUsuarios() {
        return limiteUsuarios;
    }

    public int getDisponibilidad(){
        return this.disponibilidad;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() { return fechaFin; }

    //ToString para mostrar en la informacion
    public String informacionRelevante(){
        String info = "";

        info += this.nombre + ", ";
        info += "Paradas: \n";

        int contador = 0;
        for (String parada : paradasTuristicas) {
            info += parada + ",\n";
            contador++;
            if (contador >= 2) {
                break;
            }
        }

        info += "Guia: " + this.infoGuia + "\n";
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
