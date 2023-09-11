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
    private ReservaHospedaje reserva;
    private GestionReservas gestionReservas;

    public ConfirmarReserva(GestionReservas gestionReservas, ReservaHospedaje reserva) {
        this.gestionReservas = gestionReservas;
        this.reserva = reserva;
        actualizarTablaReserva();

        btnConfirmar.addActionListener(e -> {
            this.gestionReservas.crearReserva(reserva);
            this.gestionReservas.confirmarReserva(reserva, "Efectivo");
            JOptionPane.showMessageDialog(null, "RESERVA CONFIRMADA");
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
