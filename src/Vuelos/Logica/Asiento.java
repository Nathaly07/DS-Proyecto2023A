package Vuelos.Logica;


import java.util.Scanner;

public class Asiento {

    private int numero;
    private boolean estaReservado;

    private double precio;
    private String tipo;

    public Asiento(){};

    public Asiento(int numero, double precio, String tipo) {
        this.numero = numero;
        this.estaReservado = false;
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
            this.estaReservado = true;
            return true;
        }else{
            return false;
        }


    }

    @Override
    public String toString() {
        String cadena = "num_asiento:" + this.numero + " Estado:" + this.estaReservado
                + " Precio:" + this.precio + " Tipo:" + this.tipo;
        return cadena;
    }
}



