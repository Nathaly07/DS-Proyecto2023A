package Hospedaje.InterfacesDeUsuario;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.GestionReservas;
import Hospedaje.Reservas.ReservaHospedaje;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EliminarReserva extends JFrame{
    private JPanel mainPanel;
    private JTable tbReservas;
    private DefaultTableModel tbModeloReservas;
    private JButton btnConfirmar;
    private JButton btnRegresar;
    private JTextField datosDeLaReservaTextField;
    private ReservaHospedaje reserva;
    private GestionReservas gestionReservas;

    public EliminarReserva(GestionReservas gestionReservas, ReservaHospedaje reserva) {
        this.gestionReservas = gestionReservas;
        this.reserva = reserva;
        actualizarTablaReserva();

        btnConfirmar.addActionListener(e -> {
            this.gestionReservas.eliminarReserva(reserva);
            JOptionPane.showMessageDialog(null, "RESERVA ELIMINADA");
            dispose();
        });

        btnRegresar.addActionListener(e -> {
            dispose();
        });
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

        tbModeloReservas.addRow(new Object[]{
                "Habitación ID",
                "Personas",
                "Check-In",
                "Check-Out",
                "Hotel",
                "Ciudad",
        });

        this.tbReservas.setModel(tbModeloReservas);

        this.agregarReservaATabla(reserva);
    }

    public void agregarReservaATabla(ReservaHospedaje reserva) {
        Habitacion habitacion = reserva.getHabitaciones()[0];
        tbModeloReservas.addRow(new Object[]{
                habitacion.getHabitacionID(),
                reserva.getNumeroPersonas(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                habitacion.getHotel().getNombre(),
                habitacion.getHotel().getCiudad(),
        });
    }
}
