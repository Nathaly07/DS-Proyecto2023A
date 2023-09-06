package Hospedaje.InterfacesDeUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Hospedaje.Criteria.CriteriaCiudad;
import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.GestionReservas;
import Hospedaje.Reservas.ReservaHospedaje;
import Principal.Login;
import com.toedter.calendar.JDateChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class CrearReserva extends JFrame {
    private GestionReservas gestionReservas;
    private Login login;
    private JPanel jpReservaInicio;
    private JPanel jPHospedajes;
    private JPanel jpReservaFinal;
    private JLabel txtNombreCompleto;
    private JComboBox comboBox1;
    private JTextField txtNinos;
    private JTextField txtAdultos;
    private JButton btnVerificarDisponibilidad;
    private JButton cancelarButton;
    private List<Habitacion> habitacionesDisponibles;
    private JTable tbHabitacionesDisponibles;
    private DefaultTableModel tbModeloHabitacionesDisponibles;

    JDateChooser reservacionInicio  = new JDateChooser();
    JDateChooser reservacionFinal  = new JDateChooser();
    public CrearReserva(Login login, GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
        this.login = login;

        //Calendar
        jpReservaInicio.add(reservacionInicio);
        jpReservaFinal.add(reservacionFinal);

        btnVerificarDisponibilidad.addActionListener(e -> {
            actualizarHabitacionesDisponibles();
        });

        cancelarButton.addActionListener(e -> {
            dispose();
        });

        this.txtNombreCompleto.setText(login.getUsuarioVerificado().getNombre() + " " + login.getUsuarioVerificado().getApellido());
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

        this.habitacionesDisponibles = this.gestionReservas.getHabitacionesDisponibles(reservacionInicio.getDate(), reservacionFinal.getDate());
        CriteriaCiudad criteriaCiudad = new CriteriaCiudad(comboBox1.getSelectedItem().toString());
        this.habitacionesDisponibles = criteriaCiudad.meetCriteria(this.habitacionesDisponibles);
        this.habitacionesDisponibles.forEach(this::agregarHabitacionATabla);

        tbHabitacionesDisponibles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar si es un doble clic
                    int filaSeleccionada = tbHabitacionesDisponibles.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        Habitacion habitacionSeleccionada = habitacionesDisponibles.get(filaSeleccionada - 1);
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
                this.login.getUsuarioVerificado(),
                Integer.toString(habitacionSeleccionada.getHabitacionID()),
                numeroPersonas,
                habitaciones,
                new Date(),
                reservacionInicio.getDate(),
                reservacionFinal.getDate()
        );
        ConfirmarReserva confirmarReserva = new ConfirmarReserva(this.gestionReservas, reserva);
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



