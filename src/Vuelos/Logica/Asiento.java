package Vuelos.Logica;


import java.util.Scanner;

public class Asiento {

    private int numero;
    private boolean estaReservado;

    private double precio;
    private String tipo;

    private int numFila;
    private  Vuelo vuelo;

    public Asiento(int numero,boolean estadoReservado, double precio, String tipo, int numFila,Vuelo vuelo) {
        this.numero = numero;
        this.estaReservado = estadoReservado;
        this.precio = precio;
        this.tipo = tipo;
        this.numFila = numFila;
        this.vuelo = vuelo;
    }
    public Asiento(int numero,int numFila) {
        this.numero = numero;
        this.numFila = numFila;
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
            this.estaReservado = true;
            return true;
        }else{
            return false;
        }


    }

    @Override
    public String toString() {
        String cadena = "num_asiento:" + this.numero + " Estado:" + this.estaReservado
                + " Precio:" + this.precio + " Tipo:" + this.tipo + "Num Fila" + this.numFila;
        return cadena;
    }

    public int getNumFila() {
        return numFila;
    }

}
