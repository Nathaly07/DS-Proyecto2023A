import java.util.ArrayList;
import java.util.Iterator;

public class Tour {

    private String tourID;
    private String nombre;
    private double precio;
    private ArrayList <Parada> paradasTuristicas;
    private String info_guia;
    private String duracion;
    private int limite_usuarios;
    private Gestion_Tour gestion_tour;
    private Gestion_Reserva gestion_reserva;


    //Constructor

    public Tour(String tourID, String nombre, double precio, String info_guia, String duracion, int limite_usuarios, Gestion_Tour gestion_tour, Gestion_Reserva gestion_reserva) {
        this.tourID = tourID;
        this.nombre = nombre;
        this.precio = precio;
        this.paradasTuristicas = null;
        this.info_guia = info_guia;
        this.duracion = duracion;
        this.limite_usuarios = limite_usuarios;
        this.gestion_tour = gestion_tour;
        this.gestion_reserva = gestion_reserva;
    }

    //Getters y Setters

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
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

    public boolean eliminarParada(int paradaId) {
        Iterator <Parada> it = this.paradasTuristicas.iterator();
        while(it.hasNext()) {
            Libro libro = it.next();
            if(libro.getTitulo().equalsIgnoreCase(nombre)) {
                it.remove();
                return true;
            }
        }
        return false;
    }


}
