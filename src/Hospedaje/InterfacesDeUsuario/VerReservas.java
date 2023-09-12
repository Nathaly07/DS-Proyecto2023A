package Hospedaje.InterfacesDeUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.GestionReservas;
import Hospedaje.Reservas.ReservaHospedaje;
import Principal.Sesion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VerReservas extends JFrame {
    private GestionReservas gestionReservas;
    private Sesion sesion;
    private JPanel jPReservas;
    private JLabel txtNombreCompleto;
    private JButton btnConsultar;
    private JButton btnSalir;
    private List<ReservaHospedaje> reservasUsuario;
    private JTable tbReservasRealizadas;
    private DefaultTableModel tbModeloReservas;

    public VerReservas(Sesion sesion, GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
        this.reservasUsuario = gestionReservas.getReservasPorUsuario(sesion.getUsuarioVerificado());
        this.sesion = sesion;

        btnConsultar.addActionListener(e -> {
            actualizarHabitaciones();
        });

        btnSalir.addActionListener(e -> {
            dispose();
        });

        this.txtNombreCompleto.setText(sesion.getUsuarioVerificado().getNombre() + " " + sesion.getUsuarioVerificado().getApellido());
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(jPReservas);
        setVisible(true);
    }

    public void actualizarHabitaciones() {
        this.reservasUsuario = gestionReservas.getReservasPorUsuario(sesion.getUsuarioVerificado());
        this.tbModeloReservas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas sean no editables
            }
        };
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

        this.tbReservasRealizadas.setModel(tbModeloReservas);

        this.reservasUsuario.forEach(reserva -> {
            Habitacion habitacion = reserva.getHabitaciones()[0];
            tbModeloReservas.addRow(new Object[]{
                    habitacion.getHabitacionID(),
                    reserva.getNumeroPersonas(),
                    reserva.getFechaInicio(),
                    reserva.getFechaFin(),
                    habitacion.getHotel().getNombre(),
                    habitacion.getHotel().getCiudad(),
            });
        });

        tbReservasRealizadas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar si es un doble clic
                    int filaSeleccionada = tbReservasRealizadas.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        ReservaHospedaje reservaSeleccionada = reservasUsuario.get(filaSeleccionada - 1);
                        eliminarReserva(reservaSeleccionada);
                    }
                }
            }
        });
    }

    public void eliminarReserva(ReservaHospedaje reserva) {
        EliminarReserva eliminarReserva = new EliminarReserva(this.gestionReservas, reserva);
        eliminarReserva.crearFrame();
        actualizarHabitaciones();
    }

}



