package Hospedaje.InterfacesDeUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.ModuloHospedaje;
import Hospedaje.Reservas.ReservaHospedaje;
import com.toedter.calendar.JDateChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class CrearReserva extends JFrame {
    private ModuloHospedaje moduloHospedaje;
    private JPanel jpReservaInicio;
    private JPanel jPHospedajes;
    private JPanel jpReservaFinal;
    private JTextField nombresYApellidosTextField;
    private JComboBox comboBox1;
    private JLabel txtNombres;
    private JTextField txtNinos;
    private JTextField txtAdultos;
    private JTextField txtHabitaciones;
    private JButton btnVerificarDisponibilidad;
    private JButton cancelarButton;
    private List<Habitacion> habitacionesDisponibles;
    private JTable tbHabitacionesDisponibles;
    private DefaultTableModel tbModeloHabitacionesDisponibles;

    JDateChooser reservacionInicio  = new JDateChooser();
    JDateChooser reservacionFinal  = new JDateChooser();
    public CrearReserva(ModuloHospedaje moduloHospedaje) {
        this.moduloHospedaje = moduloHospedaje;

        //Calendar
        jpReservaInicio.add(reservacionInicio);
        jpReservaFinal.add(reservacionFinal);

        btnVerificarDisponibilidad.addActionListener(e -> {
            actualizarHabitacionesDisponibles();
        });

        cancelarButton.addActionListener(e -> {
            dispose();
        });
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(jPHospedajes);
        setVisible(true);
    }

    public void actualizarHabitacionesDisponibles() {
        tbModeloHabitacionesDisponibles = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas sean no editables
            }
        };
        tbModeloHabitacionesDisponibles.addColumn("ID");
        tbModeloHabitacionesDisponibles.addColumn("Hotel");
        tbModeloHabitacionesDisponibles.addColumn("Ciudad");
        tbModeloHabitacionesDisponibles.addColumn("Precio");
        tbModeloHabitacionesDisponibles.addColumn("Maximo de Personas");

        tbModeloHabitacionesDisponibles.addRow(new Object[]{
                "ID",
                "Hotel",
                "Ciudad",
                "Precio",
                "Maximo de Personas"
        });

        this.tbHabitacionesDisponibles.setModel(tbModeloHabitacionesDisponibles);

        this.habitacionesDisponibles= moduloHospedaje.buscarHabitacionesDisponibles(reservacionInicio.getDate(), reservacionFinal.getDate());
        this.habitacionesDisponibles.forEach(this::agregarHabitacionATabla);

        tbHabitacionesDisponibles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar si es un doble clic
                    int filaSeleccionada = tbHabitacionesDisponibles.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        Habitacion habitacionSeleccionada = habitacionesDisponibles.get(filaSeleccionada);
                        confirmarReserva(habitacionSeleccionada);
                    }
                }
            }
        });
    }

    public void confirmarReserva(Habitacion habitacionSeleccionada) {
        int numeroPersonas = Integer.parseInt(txtAdultos.getText()) + Integer.parseInt(txtNinos.getText());
        Habitacion[] habitaciones = new Habitacion[1];
        habitaciones[0] = habitacionSeleccionada;
        ReservaHospedaje reserva = new ReservaHospedaje(
                "1",
                "1",
                numeroPersonas,
                habitaciones,
                new Date(),
                reservacionInicio.getDate(),
                reservacionFinal.getDate()
        );
        ConfirmarReserva confirmarReserva = new ConfirmarReserva(this.moduloHospedaje, reserva);
        confirmarReserva.crearFrame();
        dispose();
    }

    public void agregarHabitacionATabla(Habitacion habitacion) {
        // Agregar una nueva fila al modelo de la tabla
        tbModeloHabitacionesDisponibles.addRow(new Object[]{
                habitacion.getHabitacionID(),
                habitacion.getHotel().getNombre(),
                habitacion.getHotel().getCiudad(),
                habitacion.getPrecioPorNoche(),
                habitacion.getLimiteUsuarios()
        });
    }
}



