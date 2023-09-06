package Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Vehiculo {

    private ArrayList<Reseña> reseñas = new ArrayList<>();

    private int numPuerta, capacidadPasajeros;
    private double precioDeRenta;
    private String numPlaca, modelo, estadoRentaVehiculo;
    private ImageIcon imagenVehiculo;

    public String getEstadoRentaVehiculo() {
        return estadoRentaVehiculo;
    }

    public Vehiculo(ArrayList<Reseña> reseñas, int numPuerta, int capacidadPasajeros, double precioDeRenta, String numPlaca, String modelo, ImageIcon imagenVehiculo) {
        this.reseñas = reseñas;
        this.numPuerta = numPuerta;
        this.capacidadPasajeros = capacidadPasajeros;
        this.precioDeRenta = precioDeRenta;
        this.numPlaca = numPlaca;
        this.modelo = modelo;
        this.estadoRentaVehiculo = "NO RENTADO";
        this.imagenVehiculo = imagenVehiculo;
    }

    public void mostrarInformacionVehiculo(JPanel panel){


        JLabel modelolabel = new JLabel("Modelo " + modelo);
        JLabel numPlacalabel = new JLabel("Placa: " + numPlaca);
        JLabel numPuertalabel = new JLabel("Numero de Puertas: " + numPuerta);
        JLabel capacidadPasajeroslabel = new JLabel("Cantidad de pasajeros " + capacidadPasajeros);
        JLabel precioDeRentalabel = new JLabel("Precio De Renta " + precioDeRenta);
        JLabel estadoRentalabel = new JLabel("Estado de Renta " + estadoRentaVehiculo);

        String reseniasAcumuladas="Reseñas: ";
        for(Reseña reseniaAux: reseñas){
            reseniasAcumuladas += reseniaAux.toString() + " ";
        }
        JLabel resenias = new JLabel(reseniasAcumuladas);

        //Imagen
        JLabel imagen = new JLabel(new ImageIcon(imagenVehiculo.getImage().getScaledInstance(150,100, Image.SCALE_DEFAULT)));

        JButton btnRentar = new JButton("Rentar");


        btnRentar.addActionListener(e -> {
            rentar();
            estadoRentalabel.setText(estadoRentaVehiculo);
            panel.revalidate();
            panel.repaint();
        });


        panel.add(modelolabel);
        panel.add(numPlacalabel);
        panel.add(numPuertalabel);
        panel.add(capacidadPasajeroslabel);
        panel.add(precioDeRentalabel);
        panel.add(estadoRentalabel);
        panel.add(resenias);
        panel.add(imagen);
        panel.add(btnRentar);
        panel.revalidate();
        panel.repaint();

    }

    public void actualizarPrecioRenta(){
        //TODO: implementar o eliminar
    }

    private void rentar() {
        GestorRentas.agregarRenta(this);
        estadoRentaVehiculo = "RENTADO";

    }

    public double getPrecioDeRenta(){
        return this.precioDeRenta;
    }

    public String getNumPlaca(){
        return this.numPlaca;
    }

    @Override
    public String toString() {
        return numPlaca  + " " + modelo + " $" + precioDeRenta;
    }

    public void agregarDatosTabla(DefaultTableModel model) {
        Object[] infoFila = {modelo, numPlaca, numPuerta, capacidadPasajeros, precioDeRenta, estadoRentaVehiculo};
        model.addRow(infoFila);
    }
}
