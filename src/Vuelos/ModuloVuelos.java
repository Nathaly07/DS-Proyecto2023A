package Vuelos;

import Principal.Login;
import Reservas.ReservaAsiento;
import Vuelos.Logica.*;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ModuloVuelos extends JFrame{
    private JButton mostrarCatalogoButton;
    public JPanel plnPrincipalVuelos;
    private JTextField origen;
    private JTextField destino;
    private JTable table1;
    private JPanel pnlListaVuelos;
    private JPanel pnlCalendario;
    private JLabel lblFecha;
    private JButton buscarVuelosButton;
    private JButton btnSeleccionarVuelo;
    private JTabbedPane tabbedPane1;
    private JButton confirmarButton;
    private JButton eliminarButton;
    private JTable table2;
    private JButton cambiarReservaButton;
    private JTable table3;
    private GestorVuelos g = new GestorVuelos();
    private GestorReservasAsiento gestorReservasAsiento = new GestorReservasAsiento();
    private SelectorDeAsientos selectorDeAsientos;
    private JDateChooser dateChooserInicio = new JDateChooser();
    private CarritoAsientos carritoAsientos = new CarritoAsientos();

    private  Vuelo v;
    private JDialog dialog ;


    public ModuloVuelos(Login login){

        selectorDeAsientos = new SelectorDeAsientos(this);
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
                g.mostarVuelosFiltrados(table1, g.buscarVuelo(origen.getText(), destino.getText()));
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
                if(v != null) {
                    selectorDeAsientos.setVuelo(g.seleccionarVuelo(v));
                    mostrarPantallaEmergente(ModuloVuelos.this);
                } else{
                    JOptionPane.showMessageDialog(null, "Seleccione un vuelo", "Aviso", JOptionPane.ERROR_MESSAGE);

                }
                //selectorDeAsientos.crearframe();
                //setPanel(selectorDeAsientos.);
                //dispose();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table1.getSelectedRow();
                if (fila != -1) {
                    v = new Vuelo(table1.getValueAt(fila, 0).toString(),
                            table1.getValueAt(fila, 1).toString(),
                            table1.getValueAt(fila, 3).toString(),
                            table1.getValueAt(fila, 2).toString(),
                            Integer.parseInt(table1.getValueAt(fila, 4).toString()));
                }
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagoVuelos pagoVuelos = new PagoVuelos();
                pagoVuelos.crearframe();
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
        this.dialog = new JDialog(parentFrame, "Pantalla Emergente", true);

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

    public void cerrarDialog(){
        dialog.dispose();
        actualizar();
    }


    public void MostrarTabla(){
        g.mostrarVuelos(table1);
    }


    public void actualizar(){
        //ReservaAsiento reservaAsiento = new ReservaAsiento("001", "001");
        //gestorReservasAsiento.agregarResarva( new ReservaAsiento("001", "001", ));
        gestorReservasAsiento.mostrarReservas(table2);

    }


    public void crearReserva(CarritoAsientos carrito) {
        ReservaAsiento reservaAsiento = new ReservaAsiento("001", "001", carrito);
        gestorReservasAsiento.agregarResarva(reservaAsiento);

    }
}