package ModuloRentaVehiculos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarritoRentas {
    private static ArrayList<Renta> rentas;
    private static Renta renta = new Renta();

    public CarritoRentas() {
        this.rentas = new ArrayList<Renta>();
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
            for(Renta renta1: rentas){
                renta1.verInfoRenta(table,panel);
            }
        }
    }

    public static Renta getRenta() {
        return renta;
    }
}
