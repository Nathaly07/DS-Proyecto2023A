package Vehiculo;

import Principal.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ReservaVehiculo {
    ArrayList<Vehiculo> vehiculos;
    private String ciudadRetorno, ciudadDeEntrega, estadoReserva;
    private Usuario usuario; //TODO: Log in pilas
    private Date fechaInicio, fechaRetorno;
    private double totalAPagar;

    private boolean tieneVuelo;

    public ReservaVehiculo(boolean tieneVuelo) {
        this.estadoReserva = "NO PAGADO";
        this.vehiculos = new ArrayList<>();
        this.tieneVuelo = tieneVuelo;

    }

    public void reservar() {
        GestorReservaVehiculo.agregarRenta(this);
        totalAPagar = calcularReserva();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        JOptionPane.showMessageDialog(null, "SE HA AGREGADO EL CARRO DE PLACAS: " + vehiculo.getNumPlaca());
        reservar();
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        JOptionPane.showMessageDialog(null, "SE HA ELIMINADO EL VEHICULO: " + vehiculo.getNumPlaca());
    }

    public double calcularReserva() {
        long diferenciaDias = fechaRetorno.getTime() - fechaInicio.getTime();
        long daysBetween = TimeUnit.DAYS.convert(diferenciaDias, TimeUnit.MILLISECONDS);
        double precioFinal = 0;


        for (Vehiculo vehiculo : vehiculos) {
            precioFinal += vehiculo.getPrecioDeRenta() * daysBetween;
        }

        //Descuento si reservo vuelo
        if (tieneVuelo) {
            precioFinal = precioFinal * 0.9;
        }

        return precioFinal;

    }


    public boolean verificarEstado() {
        return estadoReserva.equals("NO PAGADO");
    }

    public void verInfoReserva(JTable table, JPanel panel) {

        JLabel subtotal = new JLabel("");
        //Mostrar precio final antes de descuento
        if (tieneVuelo) {
            subtotal = new JLabel("Precio antes del descuento: " + calcularReserva() / 0.9);
        }
        JLabel impuesto = new JLabel("Impuesto: "+calcularReserva()*.12);
        JLabel precioFinal = new JLabel("Precio Final Renta: " + String.format("%,.2f", calcularReserva()*1.12));

        JLabel estadoDeRenta = new JLabel("Estado de Renta: " + estadoReserva);
        DefaultTableModel model = new DefaultTableModel();

        JButton btnPagar = new JButton("Pagar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminarReserva = new JButton("Eliminar");

        btnModificar.addActionListener(e -> panel.add(new PanelModificacion(this).panelModificarReserva));


        btnPagar.addActionListener(e -> {
            pagar();
        });

        btnEliminarReserva.addActionListener(e -> {
            liberarAutos();
        });


        model.addColumn("Modelo");
        model.addColumn("Placa");
        model.addColumn("Numero de Puertas");
        model.addColumn("Capacidad Pasajeros");
        model.addColumn("Precio de Alquiler");
        model.addColumn("Estado de Renta");

        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.agregarDatosTabla(model);
        }
        table.setModel(model);

        panel.add(estadoDeRenta);//
        panel.add(new JScrollPane(table));
        panel.add(subtotal);
        panel.add(impuesto);
        panel.add(precioFinal);
        panel.add(btnPagar);
        panel.add(btnModificar);
        panel.add(btnEliminarReserva);
        panel.revalidate();
        panel.repaint();
    }

    private void pagar() {
        PagoReservaVehiculos pagoRentaVehiculos = new PagoReservaVehiculos(totalAPagar, "transferencia");
        pagoRentaVehiculos.pagar();
        estadoReserva = "PAGADO";
    }

    private void liberarAutos() {
        for (int i = vehiculos.size() - 1; i >= 0; i--) {
            vehiculos.get(i).cambiarEstado();
            vehiculos.remove(i);
        }
        GestorReservaVehiculo.eliminarRenta();
        JOptionPane.showMessageDialog(null, "Se elimino la reserva");
    }

    public void cancelarReserva() {
        for (Vehiculo vehiculos : this.vehiculos) {
            eliminarVehiculo(vehiculos);
        }
        this.estadoReserva = "CANCELADO";
        JOptionPane.showMessageDialog(null, "La reserva ha sido cancelada.");

    }

    public void modificarReserva(String ciudadEntrega, String ciudadRetorno, String fechaInicio, String fechaFin) {

        //Guardar nuevos datos de la reserva

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Define el formato de fecha esperado

        try {
            Date fechaInicioDate = dateFormat.parse(fechaInicio);
            Date fechaFinDate = dateFormat.parse(fechaFin);


            recolectarDatosReserva(ciudadEntrega, ciudadRetorno, fechaFinDate, fechaInicioDate);
        } catch (ParseException e) {
            System.out.println("El formato de la fecha es incorrecto (dd-mm-aaaa): " + e.getMessage());
        }

        System.out.println(ciudadEntrega);
        System.out.println(ciudadRetorno);
        System.out.println(fechaInicio);
        System.out.println(fechaFin);


    }

    public void recolectarDatosReserva(String ciudadOrigen, String ciudadRetorno, Date fechaFinal, Date fechaInicio) {
        this.ciudadRetorno = ciudadRetorno;
        this.ciudadDeEntrega = ciudadOrigen;
        this.fechaInicio = fechaInicio;
        this.fechaRetorno = fechaFinal;
    }

    public String getCiudadRetorno() {
        return ciudadRetorno;
    }

    public String getCiudadDeEntrega() {
        return ciudadDeEntrega;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaRetorno() {
        return fechaRetorno;
    }
}
