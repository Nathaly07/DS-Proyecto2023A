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

    public ArrayList<String> getParadasTuristicas() {
        return paradasTuristicas;
    }

    public ArrayList<String> getActividadesTuristicas() {
        return actividadesTuristicas;
    }

    public String getInfoGuia() {
        return infoGuia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void disminuirDisponibilidad(int numPersonas){
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
    public String getInformacionRelevante(){
        String info = "";

        info += this.nombre + "\n";
        info += "Paradas: \n";

        int contador = 0;
        for (String parada : paradasTuristicas) {
            info += parada + ", ";
            contador++;
            if (contador >= 4) {
                break;
            }
        }

        info += "\nGuia: " + this.infoGuia + "\n";
        info += "Duracion: " + this.duracion + "\n";

        info += "Precio: $" + this.precio;

        return info;
    }

}
