package Vuelos.Logica;


public class Asiento {

    private int numero;
    private boolean estaReservado;
    private double precio;
    private String tipo;
    private int numFila;


    public Asiento(int numero,boolean estadoReservado, double precio, String tipo, int numFila) {
        this.numero = numero;
        this.estaReservado = estadoReservado;
        this.precio = precio;
        this.tipo = tipo;
        this.numFila = numFila;
    }
    public Asiento(int numero,int numFila) {
        this.numero = numero;
        this.numFila = numFila;
    }
    public boolean isEstaReservado() {
        return estaReservado;
    }

    public void desocuparAsiento(){
        this.estaReservado = false;
    }

    public void ocuparAsiento() {
        this.estaReservado = true;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecio() {
        return precio;
    }


    public int getNumFila() {
        return numFila;
    }

    public String getTipo() {
        return tipo;
    }
}
