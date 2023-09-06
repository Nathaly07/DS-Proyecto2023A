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

    public ReservaVehiculo() {
        this.estadoReserva = "NO PAGADO";
        this.vehiculos = new ArrayList<>();
    }

    public void reservar() {
        PagoReservaVehiculos pagoRentaVehiculos = new PagoReservaVehiculos(calcularReserva(), "transferencia");
        pagoRentaVehiculos.pagar();
        estadoReserva = "PAGADO";
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        JOptionPane.showMessageDialog(null, "SE HA AGREGADO EL CARRO DE PLACAS: " + vehiculo.getNumPlaca());
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        JOptionPane.showMessageDialog(null, "SE HA ELIMINADO EL VEHICULO: " + vehiculo.getNumPlaca());
    }

    public double calcularReserva() {
        //Calcular dias
        //recores los vechiculos
        long diferenciaDias = fechaRetorno.getTime() - fechaInicio.getTime();
        long daysBetween = TimeUnit.DAYS.convert(diferenciaDias, TimeUnit.MILLISECONDS);
        double precioFinal = 0;

        for (Vehiculo vehiculo : vehiculos) {
            precioFinal += vehiculo.getPrecioDeRenta() * daysBetween;
        }
        return precioFinal;


    }


    public boolean verificarEstado() {
        return estadoReserva.equals("NO PAGADO");
    }

    public void verInfoReserva(JTable table, JPanel panel) {

        JLabel precioFinal = new JLabel("Precio Final Renta: " + calcularReserva());
        JLabel estadoDeRenta = new JLabel("Estado de Renta: " + estadoReserva);
        DefaultTableModel model = new DefaultTableModel();

        JButton btnPagar = new JButton("Pagar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminarReserva = new JButton("Eliminar");

        btnModificar.addActionListener(e -> {
            panel.add(new PanelModificacion(this).panelModificarReserva);
        });


        btnPagar.addActionListener(e -> {
            reservar();
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
        panel.add(precioFinal);
        panel.add(btnPagar);
        panel.add(btnModificar);
        panel.add(btnEliminarReserva);
        panel.revalidate();
        panel.repaint();
    }

    private void liberarAutos() {
        for (int i = vehiculos.size() - 1; i >= 0; i--) {
            vehiculos.get(i).cambiarEstado();
            vehiculos.remove(i);
        }


        GestoReservaVehiculo.eliminarRenta();
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
        //TODO IMPLEMENTAR
        System.out.println("hola");

        //Guardar nuevos datos de la reserva

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Define el formato de fecha esperado

        try {
            Date fechaInicioDate = dateFormat.parse(fechaInicio);
            Date fechaFinDate = dateFormat.parse(fechaFin);


            recolectarDatosReserva(ciudadEntrega, ciudadRetorno, fechaInicioDate, fechaFinDate);
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
