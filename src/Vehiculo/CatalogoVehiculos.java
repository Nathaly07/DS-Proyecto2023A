package Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogoVehiculos {
    private ArrayList<Vehiculo> vehiculos;

    public CatalogoVehiculos() {
        //Por la composición
        this.vehiculos = new ArrayList<Vehiculo>();

        ArrayList<Reseña> reseñas = new ArrayList<Reseña>();
        reseñas.add(new Reseña("Bien", 10));
        reseñas.add(new Reseña("Mal", 0));

        ArrayList<Reseña> reseñas1 = new ArrayList<Reseña>();
        reseñas.add(new Reseña("", 10));
        reseñas.add(new Reseña("Mal", 0));

        this.vehiculos.add(
                new Vehiculo(
                        reseñas,
                        4,
                        4,
                        160.0,
                        "ABC-1234",
                        "BMW",

                        new ImageIcon(getClass().getResource("ImagenesVehiculos/bmw.png"))

                ));


        this.vehiculos.add(
                new Vehiculo(
                        reseñas,
                        4,
                        4,
                        110.0,
                        "DEF-1234",
                        "Toyota",
                        new ImageIcon(getClass().getResource("ImagenesVehiculos/toyota.png"))

                ));

        this.vehiculos.add(
                new Vehiculo(
                        reseñas,
                        4,
                        5,
                        129.0,
                        "GHI-1234",
                        "Ford",
                        new ImageIcon(getClass().getResource("ImagenesVehiculos/ford.jpg"))

                ));
    }

    public void mostrarVehiculos(JPanel panel) {
        panel.setLayout(new GridLayout(0,3));
        panel.removeAll();
        for (Vehiculo vehiculo : vehiculos) {
            if (!vehiculo.getEstadoRentaVehiculo().equals("RENTADO"))
                vehiculo.mostrarInformacionVehiculo(panel);
        }
    }

    public void añadirVehiculoAlCatalogo(){
        //TODO implementar

    }
    public void eliminarVehiculoAlCatalogo(){
        //TODO implementar
    }

    public void actualizarCatalogo(){
        //TODO implementar
    }


}
