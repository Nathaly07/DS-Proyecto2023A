package Hospedaje.InterfacesDeUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class VerHabitacionesDisponibles extends JFrame {
    private JPanel jpHospedajesDisponibles;
    private JComboBox comboBox1;
    private JPanel jpReservaFinal;
    private JPanel jpReservaInicio;
    private JButton salirButton;
    private JTextField textField1;
    private DefaultTableModel modeloTabla;
    private JTable tablaHabitaciones;
    private JButton verificarDesponibilidadButton;

    JDateChooser reservacionInicio  = new JDateChooser();
    JDateChooser reservacionFinal  = new JDateChooser();
    public VerHabitacionesDisponibles(){
        //Calendar
        jpReservaInicio.add(reservacionInicio);
        jpReservaFinal.add(reservacionFinal);
    }
    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(jpHospedajesDisponibles);
        setVisible(true);
    }

    public void actualizarHabitacionesDisponibles() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Hotel");
        modeloTabla.addColumn("Ciudad");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Maximo de Personas");

        tablaHabitaciones = new JTable();

    }
}