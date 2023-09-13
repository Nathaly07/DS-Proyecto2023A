package Hospedaje.InterfacesDeUsuario;

import Hospedaje.Reservas.GestionReservas;
import Hospedaje.Reservas.ReservaHospedaje;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConfirmarReserva extends JFrame{
    private JPanel mainPanel;
    private JTable tbReservas;
    private DefaultTableModel tbModeloReservas;
    private JButton btnConfirmar;
    private JButton btnRegresar;
    private int personas;
    private JLabel txtSubtotal;
    private JLabel txtHabitacion;
    private JLabel txtPrecioPorNoche;
    private JLabel txtCantidad;
    private JLabel txtValorTotal;
    private JLabel txtIVA;
    private JLabel txtTotal;
    private JLabel txtDias;
    private ReservaHospedaje reserva;
    private GestionReservas gestionReservas;

    public ConfirmarReserva(GestionReservas gestionReservas, ReservaHospedaje reserva, int personas) {
        this.gestionReservas = gestionReservas;
        this.reserva = reserva;
        this.personas = personas;

        btnConfirmar.addActionListener(e -> {
            this.gestionReservas.crearReserva(reserva);
            this.gestionReservas.confirmarReserva(reserva, "Efectivo");
            JOptionPane.showMessageDialog(null, "RESERVA CONFIRMADA");
            dispose();
        });

        btnRegresar.addActionListener(e -> {
            dispose();
        });

        this.actualizarTablaReserva();
    }

    public void crearFrame(){
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Ver Reserva");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actualizarTablaReserva() {
        tbModeloReservas = new DefaultTableModel();
        tbModeloReservas.addColumn("ID");
        tbModeloReservas.addColumn("Hotel");
        tbModeloReservas.addColumn("Ciudad");
        tbModeloReservas.addColumn("Precio");
        tbModeloReservas.addColumn("Maximo de Personas");
        tbModeloReservas.addColumn("Fecha de Inicio");
        tbModeloReservas.addColumn("Fecha de Salida");

        tbModeloReservas.addRow(new Object[]{
                "ID",
                "Hotel",
                "Ciudad",
                "Precio",
                "Maximo de Personas",
                "Fecha de Inicio",
                "Fecha de Salida"
        });

        this.tbReservas.setModel(tbModeloReservas);

        this.agregarReservaATabla(reserva);

        this.txtHabitacion.setText(reserva.getHabitaciones()[0].getHotel().getNombre() + " - " + reserva.getHabitaciones()[0].getHotel().getCiudad() + " - " + reserva.getHabitaciones()[0].getHabitacionID());
        this.txtPrecioPorNoche.setText(String.valueOf(reserva.getHabitaciones()[0].getPrecioPorNoche()));
        this.txtCantidad.setText(String.valueOf(this.personas));
        this.reserva.generarPrecio();
        // Dias
        this.txtDias.setText(String.valueOf(this.reserva.getDias()));

        // Subtotal
        this.txtValorTotal.setText(this.reserva.getSubtotal() + "");
        this.txtSubtotal.setText(this.reserva.getSubtotal() + "");

        // IVA
        this.txtIVA.setText(this.reserva.getIVA() + "");

        // Total
        this.txtTotal.setText(this.reserva.getTotal() + "");
    }

    public void agregarReservaATabla(ReservaHospedaje reserva) {
        // Agregar una nueva fila al modelo de la tabla
        tbModeloReservas.addRow(new Object[]{
                reserva.getReservaId(),
                reserva.getHabitaciones()[0].getHotel().getNombre(),
                reserva.getHabitaciones()[0].getHotel().getCiudad(),
                reserva.getHabitaciones()[0].getPrecioPorNoche(),
                reserva.getHabitaciones()[0].getLimiteUsuarios(),
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        });
    }
}
