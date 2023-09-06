package Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Renta  {
    ArrayList<Vehiculo> vehiculos;
    private String ciudadRetorno, ciudadDeEntrega, estadoRenta;
    private int idArrendatario; //TODO: eliminar el id Del Arrendatario
    private Date fechaInicio, fechaRetorno;

    public Renta(){
        this.estadoRenta="NO PAGADO";
        this.vehiculos = new ArrayList<>();
    }

    public void rentar(){

    }

    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
        JOptionPane.showMessageDialog(null,"SE HA AGREGADO EL CARRO DE PLACAS: " + vehiculo.getNumPlaca());
    }

    public void eliminarVehiculo(){
        //TODO Implementar
    }

    public double calcularRenta(){
        //Calcular dias
        //recores los vechiculos
        long diferenciaDias = fechaRetorno.getTime() - fechaInicio.getTime();
        long daysBetween = TimeUnit.DAYS.convert(diferenciaDias, TimeUnit.MILLISECONDS);
        double precioFinal = 0;

        for(Vehiculo vehiculo: vehiculos){
            precioFinal += vehiculo.getPrecioDeRenta()*daysBetween;
        }
        return precioFinal;


    }



    public boolean verificarEstado(){
        return estadoRenta.equals("NO PAGADO");
    }

    public void verInfoRenta(JTable table, JPanel panel){

        JLabel precioFinal = new JLabel("Precio Final Renta: "+calcularRenta());
        JLabel estadoDeRenta = new JLabel("Estado de Renta: "+estadoRenta);
        DefaultTableModel model = new DefaultTableModel();

        JButton btnPagar = new JButton("Pagar");

        btnPagar.addActionListener(e -> {
            PagoRentaVehiculos pagoRentaVehiculos = new PagoRentaVehiculos(calcularRenta(),"transferencia");
            pagoRentaVehiculos.pagar();
            estadoRenta = "PAGADO";
        });

        model.addColumn("Modelo");
        model.addColumn("Placa");
        model.addColumn("Numero de Puertas");
        model.addColumn("Capacidad Pasajeros");
        model.addColumn("Precio de Alquiler");
        model.addColumn("Estado de Renta");

        for(Vehiculo vehiculo : vehiculos){
            vehiculo.agregarDatosTabla(model);
        }
        table.setModel(model);

        panel.add(estadoDeRenta);//
        panel.add(new JScrollPane(table));
        panel.add(precioFinal);
        panel.add(btnPagar);
        panel.revalidate();
        panel.repaint();

    }

    public void cancelarReserva() {
        //TODO IMPLEMENTAR

    }

    public void modificarReserva() {
        //TODO IMPLEMENTAR
    }
    public void recolectarDatosRenta(String ciudadOrigen, String ciudadRetorno, Date fechaFinal, Date fechaInicio){
        this.ciudadRetorno = ciudadRetorno;
        this.ciudadDeEntrega = ciudadOrigen;
        this.fechaInicio = fechaInicio;
        this.fechaRetorno = fechaFinal;
        //TODO quitar Prints
        System.out.println(ciudadOrigen+"\n"+ciudadRetorno+"\n"+fechaInicio+"\n"+fechaRetorno);
    }


}
