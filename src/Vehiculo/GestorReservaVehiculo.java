package Vehiculo;

import Vuelos.Logica.GestorReservasAsiento;

import javax.swing.*;
import java.util.ArrayList;

public class GestorReservaVehiculo {
    private static ArrayList<ReservaVehiculo> rentas;
    private static ReservaVehiculo renta;
    private  CatalogoVehiculos catalogo = new CatalogoVehiculos();

    public CatalogoVehiculos getCatalogo() {
        return catalogo;
    }

    public GestorReservaVehiculo(GestorReservasAsiento gra) {
        this.rentas = new ArrayList<ReservaVehiculo>();
        renta = new ReservaVehiculo(gra.verificarReservasAsientos());
    }

    public static void agregarRenta(ReservaVehiculo renta) {
        rentas.add(renta);

    }

    public static void eliminarRenta() {
        rentas.remove(renta);
        //renta = new ReservaVehiculo(gra.verificarReservasAsientos());
    }


    public void verInfoCarrito(JTable table, JPanel panel) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, 1));
        if (rentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO HAY NINGUNA RENTA");
        } else {
            for (ReservaVehiculo renta1 : rentas) {
                renta1.verInfoReserva(table, panel);
            }
        }
    }

    public static ReservaVehiculo getRenta() {
        return renta;
    }
}
