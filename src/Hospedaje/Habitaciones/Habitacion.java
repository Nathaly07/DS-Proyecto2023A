package Hospedaje.Habitaciones;

import java.util.Date;

public class Habitacion {
    private int habitacionID;
    private double precio;
    private int limiteUsuarios;
    private Hotel hotel;

    public Habitacion(int habitacionID, double precio, int limiteUsuarios, Hotel hotel) {
        this.habitacionID = habitacionID;
        this.precio = precio;
        this.limiteUsuarios = limiteUsuarios;
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public int getHabitacionID() {
        return this.habitacionID;
    }

    public String getInfo(){
        return "\nHabitaciónId: " + this.habitacionID +
                "\nPrecio: " + this.precio +
                "\nLímite de Usuarios: " + this.limiteUsuarios +
                "\nHotel: " + this.hotel.getNombre() + "\n";
    }

}