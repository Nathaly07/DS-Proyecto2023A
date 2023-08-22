package Hospedaje.Habitaciones;

public class Habitacion {
    private int habitacionID;
    private double precio;
    private int limiteUsuarios;
    private boolean disponibilidad;
    private Hotel hotel;

    public Habitacion(int habitacionID, double precio, int limiteUsuarios, boolean disponibilidad, Hotel hotel) {
        this.habitacionID = habitacionID;
        this.precio = precio;
        this.limiteUsuarios = limiteUsuarios;
        this.disponibilidad = disponibilidad;
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public boolean estaDisponible() {
        return this.disponibilidad;
    }

    public String getInfo(){
        return "\nHabitación: " + this.habitacionID +
                "\nPrecio: " + this.precio +
                "\nLímite de Usuarios: " + this.limiteUsuarios +
                "\nDisponibilidad: " + this.disponibilidad +
                "\nHotel: " + this.hotel.getNombre() + "\n";
    }

}