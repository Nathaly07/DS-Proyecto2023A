package Vuelos.Logica;

import Vuelos.ReservaAsiento;

import java.util.List;

public class PagoReservaAsiento {
    private double monto;
    private List<ReservaAsiento> reservas;


    public PagoReservaAsiento(List<ReservaAsiento> reservas){
        this.reservas = reservas;
    }


    // cantidad de asientos premium reservados

    public int cantidadAsientosPremiumReservados(){
        int cantidad = 0;
        for (ReservaAsiento r: this.reservas){
            cantidad += r.cantidadAsientosReservadosPremium();
        }
        return cantidad;
    }

    // cantidad de asientos turista reservados
    public int cantidadAsientosTuristaReservados(){
        int cantidad = 0;
        for (ReservaAsiento r: this.reservas){
            cantidad += r.cantidadAsientosReservadosTurista();
        }
        return cantidad;
    }

    //calcular costo total de los asientos premium reservados
    public double calcularCostoTotalPremium(){
        double total = 0;
        for (ReservaAsiento r: this.reservas){
            total += r.generarCostoTotalPremium();
        }
        return total;
    }

    //calcular costo total de los asientos turista reservados
    public double calcularCostoTotalTurista(){
            double total = 0;
            for (ReservaAsiento r: this.reservas){
                total += r.generarCostoTotalTurista();
            }
            return total ;
    }

    //calcular costo total de los asientos reservados
    public double calcularCostoTotalReservados(){
        double total = 0;
        total = calcularCostoTotalPremium() + calcularCostoTotalTurista();
        return total;
    }


    //IVA
    public double calcularIVA(){
        double total = 0;
        total = calcularCostoTotalReservados() * 0.12;
        return total;
    }

    //calcular costo total
    public double calcularCostoTotal(){
        double total = 0;
        total = calcularCostoTotalReservados() + calcularIVA();
        return total;
    }



}
