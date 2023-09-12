package Vuelos;

import Principal.Sesion;

import Vuelos.Logica.ReservaAsiento;

import Vuelos.Logica.*;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private JButton cancelarButton;
    private JTable table2;
    private JTable table3;
    private GestorVuelos gestorVuelos = new GestorVuelos();
    private GestorReservasAsiento gestorReservasAsiento = new GestorReservasAsiento();
    private SelectorDeAsientos selectorDeAsientos;
    private JDateChooser dateChooserFechaVuelos = new JDateChooser();
    private PagoVuelos pagoVuelos;
    private  Vuelo v,v2;
    private JDialog dialog ;
    private Sesion sesion;
    private Date fechaComun;
    private String destinoComun;



    public ModuloVuelos(Sesion sesion){
        this.sesion = sesion;
        this.fechaComun = sesion.getFechaComun();
        this.destinoComun = sesion.getDestinoComun();

        selectorDeAsientos = new SelectorDeAsientos(this);
        pagoVuelos = new PagoVuelos(this);
        pnlListaVuelos.setVisible(true);
        mostrarVuelosConDatosIniciales();
        mostrarCatalogoButton.addActionListener(e -> pnlListaVuelos.setVisible(true));


        //calendario
        pnlCalendario.add(dateChooserFechaVuelos);
        buscarVuelosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean bandera = true;
                String fechaFormateada = "";

                if(dateChooserFechaVuelos.getDate() == null){
                    bandera = false;
                }else {
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    fechaFormateada = formato.format(dateChooserFechaVuelos.getDate());
                }
                if(!origen.getText().isEmpty() && !destino.getText().isEmpty() && bandera ){
                    gestorVuelos.mostarVuelosFiltrados(table1, gestorVuelos.filtar(origen.getText().toString(),destino.getText().toString(), fechaFormateada));
                }else if(!origen.getText().isEmpty() && !destino.getText().isEmpty()) {
                    gestorVuelos.mostarVuelosFiltrados(table1, gestorVuelos.buscarVuelo(origen.getText(), destino.getText()));
                } else if(bandera && origen.getText().isEmpty() &&  destino.getText().isEmpty()){
                    gestorVuelos.mostarVuelosFiltrados(table1, gestorVuelos.buscarVueloFecha(fechaFormateada));
                } else {
                    JOptionPane.showMessageDialog(null, "Datos insuficientes para la busqueda", "Aviso", JOptionPane.ERROR_MESSAGE);
                }
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
                    if(VerificarReserva()) {
                        selectorDeAsientos.setVuelo(gestorVuelos.seleccionarVuelo(v));
                        mostrarPantallaEmergente(ModuloVuelos.this);
                        v = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe un reserva con ese vuelo", "Aviso", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Seleccione un vuelo", "Aviso", JOptionPane.ERROR_MESSAGE);

                }

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
                if (gestorReservasAsiento.CantidadReservasPendientes() >0) {
                    if (v2 != null) {
                        CarritoAsientos carritoAsientos = new CarritoAsientos(v2);
                        ReservaAsiento reserva = new ReservaAsiento(carritoAsientos, sesion.getUsuarioVerificado());
                        pagoVuelos.InicioDatos(gestorReservasAsiento.SeleccionarReserva(reserva));
                        mostrarPagoVuelos(ModuloVuelos.this);
                        v2 = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una reserva", "Aviso", JOptionPane.ERROR_MESSAGE);

                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No existe reservas pendientes", "Aviso", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table2.getSelectedRow();
                if (fila != -1) {
                    v2 = new Vuelo(table2.getValueAt(fila, 1).toString(),
                            table2.getValueAt(fila, 2).toString(),
                            table2.getValueAt(fila, 3).toString(),
                            table2.getValueAt(fila, 4).toString());
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorReservasAsiento.CantidadReservasPendientes() >0) {
                    if (v2 != null) {
                        CarritoAsientos carritoAsientos = new CarritoAsientos(v2);
                        ReservaAsiento reserva = new ReservaAsiento(carritoAsientos, sesion.getUsuarioVerificado());
                        gestorReservasAsiento.SeleccionarReserva(reserva).cancelarReserva();
                        actualizar();
                        JOptionPane.showMessageDialog(null, "Reserva cancelada con exito", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
                        v2 = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una reserva", "Aviso", JOptionPane.ERROR_MESSAGE);

                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No existe reservas pendientes", "Aviso", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }

    private void mostrarVuelosConDatosIniciales() {
        String fechaFormateada = "";
        if(destinoComun != null ){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fechaFormateada = formato.format(fechaComun);
            gestorVuelos.mostarVuelosFiltrados(table1, gestorVuelos.buscarVueloPorDestinoFecha(destinoComun, fechaFormateada));
        }
        
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

        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Hacer visible la pantalla emergente
        dialog.setVisible(true);


    }

    public void cerrarDialog(){
        dialog.dispose();
        MostrarTabla();
        actualizar();
    }


    public void MostrarTabla(){
        gestorVuelos.mostrarVuelos(table1);
    }
    private void mostrarPagoVuelos(JFrame parentFrame) {
        this.dialog = new JDialog(parentFrame, "Pantalla Emergente", true);
        dialog.add(pagoVuelos.JPPagoVuelos);
        dialog.setSize(804, 604);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private void mostrarSelectorDeAsiento(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Pantalla Emergente", true);
        dialog.add(selectorDeAsientos.pnlPrincipalAsientos);
        dialog.setSize(804, 604);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }


    public void actualizar(){
        gestorReservasAsiento.reservasPendientes(table2);
        gestorReservasAsiento.mostrarReservas(table3);
        gestorVuelos.mostrarVuelos(table1);
    }


    public void crearReserva(CarritoAsientos lista) {
        ReservaAsiento reservaAsiento = new ReservaAsiento(new CarritoAsientos(lista.getAsientos(),lista.getVuelo()), sesion.getUsuarioVerificado());
        reservaAsiento.crearReserva();
        gestorReservasAsiento.agregarResarva(reservaAsiento);
    }

    public boolean VerificarReserva(){
        ComparadorVuelo com = new ComparadorVuelo();
        for (int i = 0; i < table2.getRowCount(); i++) {
            Vuelo aux = new Vuelo(table2.getValueAt(i,1).toString(),
                    table2.getValueAt(i,2).toString(),
                    table2.getValueAt(i,3).toString(),
                    table2.getValueAt(i,4).toString());
            if(com.compare(aux, this.v) == 1 ){
                return false;
            }
        }
        return true;
    }
}

