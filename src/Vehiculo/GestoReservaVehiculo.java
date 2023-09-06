package Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//TODO: La clase gestor es un antipatrón podemos usar solo la renta en lugar del gestor.
public class GestoReservaVehiculo {
    private static ArrayList<ReservaVehiculo> rentas;
    private static ReservaVehiculo renta = new ReservaVehiculo();

    public GestoReservaVehiculo() {
        this.rentas = new ArrayList<ReservaVehiculo>();
    }

    public static void agregarRenta(Vehiculo vehiculo){
        if(rentas.isEmpty()){
            rentas.add(renta);
            if(renta.verificarEstado()){
                renta.agregarVehiculo(vehiculo);
            }
        }else{
            if(renta.verificarEstado()){
                renta.agregarVehiculo(vehiculo);
            }
        }

    }
    public void eliminarRenta(){
        //TODO: implementar
    }


    public  void verInfoCarrito(JTable table, JPanel panel){
        panel.removeAll();
        panel.setLayout(new GridLayout(0,1));
        if(rentas.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO HAY NINGUNA RENTA");
        }else{
            for(ReservaVehiculo renta1: rentas){
                renta1.verInfoReserva(table,panel);
            }
        }
    }

    public static ReservaVehiculo getRenta() {
        return renta;
    }
}
