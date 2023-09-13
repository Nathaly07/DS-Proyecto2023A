package Hospedaje.InterfacesDeUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Hospedaje.Criteria.CriteriaCiudad;
import Hospedaje.Criteria.CriteriaPorPersonas;
import Hospedaje.Criteria.CriteriaTipoHabitacion;
import Hospedaje.Habitaciones.CaracteristicasHabitacion;
import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.GestionReservas;
import Hospedaje.Reservas.ReservaHospedaje;
import Principal.Sesion;
import com.toedter.calendar.JDateChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class CrearReserva extends JFrame {
    private GestionReservas gestionReservas;
    private Sesion sesion;
    private JPanel jpReservaInicio;
    private JPanel jPHospedajes;
    private JPanel jpReservaFinal;
    private JLabel txtNombreCompleto;
    private JComboBox comboBoxCiudad;
    private JTextField txtNinos;
    private JTextField txtAdultos;
    private JButton btnVerificarDisponibilidad;
    private JButton cancelarButton;
    private List<Habitacion> habitacionesDisponibles;
    private JTable tbHabitacionesDisponibles;
    private JCheckBox checkboxMascotas;
    private JRadioButton checkboxFumadores;
    private DefaultTableModel tbModeloHabitacionesDisponibles;

    JDateChooser reservacionInicio  = new JDateChooser();
    JDateChooser reservacionFinal  = new JDateChooser();
    public CrearReserva(Sesion sesion, GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
        this.sesion = sesion;

        //Calendar
        jpReservaInicio.add(reservacionInicio);
        jpReservaFinal.add(reservacionFinal);

        btnVerificarDisponibilidad.addActionListener(e -> {
            actualizarHabitacionesDisponibles();
        });

        cancelarButton.addActionListener(e -> {
            dispose();
        });

        this.txtNombreCompleto.setText(sesion.getUsuarioVerificado().getNombre() + " " + sesion.getUsuarioVerificado().getApellido());

        if(this.sesion.getDestinoComun() != null){
            this.comboBoxCiudad.setSelectedItem(this.sesion.getDestinoComun());
        }

        if(this.sesion.getFechaComun() != null){
            this.reservacionInicio.setDate(this.sesion.getFechaComun());
        }

        this.actualizarHabitacionesDisponibles();
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
        tbModeloHabitacionesDisponibles.addColumn("Fumadores");
        tbModeloHabitacionesDisponibles.addColumn("Mascotas");

        tbModeloHabitacionesDisponibles.addRow(new Object[]{
                "ID",
                "Hotel",
                "Ciudad",
                "Precio",
                "Máximo de Personas",
                "Fumadores",
                "Mascotas"
        });

        this.tbHabitacionesDisponibles.setModel(tbModeloHabitacionesDisponibles);

        // Se obtienen todas las habitaciones
        if(reservacionInicio.getDate() == null || reservacionFinal.getDate() == null){
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de entrada o salida");
            return;
        }

        this.habitacionesDisponibles = this.gestionReservas.getHabitacionesDisponibles(reservacionInicio.getDate(), reservacionFinal.getDate());

        // Filtro por ciudad
        if(comboBoxCiudad.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Ingrese una ciudad");
            return;
        }
        CriteriaCiudad criteriaCiudad = new CriteriaCiudad(comboBoxCiudad.getSelectedItem().toString());
        this.habitacionesDisponibles = criteriaCiudad.meetCriteria(this.habitacionesDisponibles);

        // Filtro por fumadores
        if(checkboxFumadores.isSelected()){
            CriteriaTipoHabitacion criteriaTipoHabitacion = new CriteriaTipoHabitacion(CaracteristicasHabitacion.FUMADORES);
            this.habitacionesDisponibles = criteriaTipoHabitacion.meetCriteria(this.habitacionesDisponibles);
        }

        // Filtro por Mascotas
        if(checkboxMascotas.isSelected()){
            CriteriaTipoHabitacion criteriaTipoHabitacion = new CriteriaTipoHabitacion(CaracteristicasHabitacion.MASCOTAS);
            this.habitacionesDisponibles = criteriaTipoHabitacion.meetCriteria(this.habitacionesDisponibles);
        }

        // Filtro por numero de personas
        int numeroPersonas = 0;

        try {
            numeroPersonas = Integer.parseInt(txtAdultos.getText()) + Integer.parseInt(txtNinos.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido de personas");
            return;
        }

        if(numeroPersonas == 0){
            JOptionPane.showMessageDialog(null, "Ingrese un número válido de personas");
            return;
        }

        CriteriaPorPersonas criteriaPorPersonas = new CriteriaPorPersonas(numeroPersonas);
        this.habitacionesDisponibles = criteriaPorPersonas.meetCriteria(this.habitacionesDisponibles);


        // Se agregan las habitaciones a la tabla en la UI
        this.habitacionesDisponibles.forEach(this::agregarHabitacionATabla);

        tbHabitacionesDisponibles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar si es un doble click
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
                this.sesion.getUsuarioVerificado(),
                Integer.toString(habitacionSeleccionada.getHabitacionID()),
                numeroPersonas,
                habitaciones,
                new Date(),
                reservacionInicio.getDate(),
                reservacionFinal.getDate()
        );
        ConfirmarReserva confirmarReserva = new ConfirmarReserva(this.gestionReservas, reserva, numeroPersonas);
        confirmarReserva.crearFrame();
        dispose();
    }

    public void agregarHabitacionATabla(Habitacion habitacion) {
        String fumadores = "No";
        String mascotas = "No";

        for (CaracteristicasHabitacion caracteristica : habitacion.getCaracteristicas()) {
            if (caracteristica.equals(CaracteristicasHabitacion.FUMADORES)) {
                fumadores = "Si";
            }

            if (caracteristica.equals(CaracteristicasHabitacion.MASCOTAS)) {
                mascotas = "Si";
            }
        }

        tbModeloHabitacionesDisponibles.addRow(new Object[]{
                habitacion.getHabitacionID(),
                habitacion.getHotel().getNombre(),
                habitacion.getHotel().getCiudad(),
                habitacion.getPrecioPorNoche(),
                habitacion.getLimiteUsuarios(),
                fumadores,
                mascotas
        });
    }
}



