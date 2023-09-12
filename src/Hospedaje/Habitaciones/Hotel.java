package Hospedaje.Habitaciones;

import java.io.Serializable;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 2L;
    private String nombre;
    private String direccion;
    private String ciudad;
    private int categoria; // En estrellas

    public Hotel(String nombre, String direccion, String ciudad, int categoria) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public int getCategoria() {
        return this.categoria;
    }
}
