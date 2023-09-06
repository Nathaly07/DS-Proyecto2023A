package Hospedaje.Habitaciones;

import java.util.Date;

public class Habitacion {
    private int habitacionID;
    private double precioPorNoche;
    private int limiteUsuarios;
    private Hotel hotel;

    public Habitacion(int habitacionID, double precioPorNoche, int limiteUsuarios, Hotel hotel) {
        this.habitacionID = habitacionID;
        this.precioPorNoche = precioPorNoche;
        this.limiteUsuarios = limiteUsuarios;
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public int getHabitacionID() {
        return this.habitacionID;
    }

    public double getPrecioPorNoche() {
        return this.precioPorNoche;
    }

    public int getLimiteUsuarios() {
        return this.limiteUsuarios;
    }

    public String getCiudad() {
        return this.hotel.getCiudad();
    }

    public String getInfo(){
        return "\nHabitaciónId: " + this.habitacionID +
                "\nPrecioPorNoche: " + this.precioPorNoche +
                "\nLímite de Usuarios: " + this.limiteUsuarios +
                "\nHotel: " + this.hotel.getNombre() + "\n";
    }

}