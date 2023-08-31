package Vuelos.Logica;

import java.util.List;

public class PagoReservaAsiento {
    private double monto;
    private List<ReservaAsiento> reservas;

    public PagoReservaAsiento(List<ReservaAsiento> reservas){
        this.reservas = reservas;
    }
    public double calcularCostoTotal(){
        double total = 0;
        for (ReservaAsiento r: this.reservas){
            total += r.generarCostoTotal();
        }
        return total * 1.12;

    }
    

}
