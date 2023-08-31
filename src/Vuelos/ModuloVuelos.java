package Vuelos;

import Principal.Login;
import Vuelos.Logica.*;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModuloVuelos extends JFrame{
    private JButton mostrarCatalogoButton;
    public JPanel plnPrincipalVuelos;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JPanel pnlListaVuelos;
    private JPanel pnlCalendario;
    private JLabel lblFecha;
    private JButton buscarVuelosButton;
    private JButton btnSeleccionarVuelo;
    private GestorVuelos g = new GestorVuelos();
    private SelectorDeAsientos selectorDeAsientos = new SelectorDeAsientos();
    private JDateChooser dateChooserInicio = new JDateChooser();


    public ModuloVuelos(Login login){
        pnlListaVuelos.setVisible(true);
        mostrarCatalogoButton.addActionListener(e -> pnlListaVuelos.setVisible(true));
        //regresarButton.addActionListener(e -> {
        //    Módulos módulos = new Módulos(login);
        //    módulos.crearFrame();
        //    dispose();
        //});


       //salirButton.addActionListener(e -> {
       //    dispose();
       //    System.exit(0);
       //});

        //calendario
       pnlCalendario.add(dateChooserInicio);
        buscarVuelosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.mostarVuelosFiltrados(table1, g.buscarVuelo(textField1.getText(),textField2.getText()));
            }
        });
        mostrarCatalogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla();
            }
        });
        btnSeleccionarVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPantallaEmergente(ModuloVuelos.this);
                //selectorDeAsientos.crearframe();
                //setPanel(selectorDeAsientos.);
                //dispose();
            }
        });
    }

    public void crearframe() {
        setTitle("Modulo Vuelos");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarPantallaEmergente(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Pantalla Emergente", true);

        // Configurar el contenido de la pantalla emergente
        //JLabel label = new JLabel("Esto es una pantalla emergente.");

        dialog.add(selectorDeAsientos.pnlPrincipalAsientos);

        // Configurar el tamaño de la pantalla emergente
        dialog.setSize(804, 604);

        // Centrar la pantalla emergente en la ventana principal
        dialog.setLocationRelativeTo(parentFrame);

        // Hacer visible la pantalla emergente
        dialog.setVisible(true);
    }
    public void MostrarTabla(){
        g.mostrarVuelos(table1);
    }

}
