package Vuelos.Logica;


import java.util.Scanner;

public class Asiento {

    private int numero;
    private boolean estaReservado;
    private String posicion;
    private double precio;
    private String tipo;

    public Asiento(int numero, int estaReservado, String posicion, double precio, String tipo) {
        this.numero = numero;
        this.posicion = posicion;
        if (estaReservado == 0) {
            this.estaReservado = true;
        } else {
            this.estaReservado = false;
        }
        this.precio = precio;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEstaReservado() {
        return estaReservado;
    }

    public void CancelarReservaAsiento(){
        this.estaReservado = false;
    }

    public boolean reservar() {
        System.out.println("Desea reservar este asiento yes or not");
        Scanner scanner = new Scanner(System.in);
        String bandera = scanner.nextLine();
        if(bandera.equalsIgnoreCase("yes")){
            this.estaReservado = false;
            return true;
        }else{
            return false;
        }


    }

    @Override
    public String toString() {
        String cadena = "num_asiento:" + this.numero + " Estado:" + this.estaReservado + " Posicion:" + this.posicion
                + " Precio:" + this.precio + " Tipo:" + this.tipo;
        return cadena;
    }
}


