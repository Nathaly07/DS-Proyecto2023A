package Vuelos.GUI;

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
    private JButton btnMostrarCatalogo;
    public JPanel plnPrincipalVuelos;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JTable tblCatalogoDeVuelos;
    private JPanel pnlListaVuelos;
    private JPanel pnlCalendario;
    private JLabel lblFecha;
    private JButton btnBuscarVuelos;
    private JButton btnSeleccionarVuelo;
    private JTabbedPane tpnReservas;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JTable tblReservasPendientes;
    private JTable tblHistorialReservas;
    private GestorVuelos gestorVuelos = new GestorVuelos();
    private GestorReservasAsiento gestorReservasAsiento;
    private SelectorDeAsientos selectorDeAsientos;
    private JDateChooser dateChooserFechaVuelos = new JDateChooser();
    private PagoVuelos pagoVuelos;
    private  Vuelo vueloSeleccionado, reservaVueloSeleccionado;
    private JDialog digVentanaSelectorAsiento;
    private Sesion sesion;
    private Date fechaComun;
    private String destinoComun;

    public ModuloVuelos(Sesion sesion, GestorReservasAsiento gra){
        this.sesion = sesion;
        this.fechaComun = sesion.getFechaComun();
        this.destinoComun = sesion.getDestinoComun();
        this.gestorReservasAsiento = gra;

        selectorDeAsientos = new SelectorDeAsientos(this);
        pagoVuelos = new PagoVuelos(this);
        pnlListaVuelos.setVisible(true);
        mostrarVuelosConDatosIniciales();
        btnMostrarCatalogo.addActionListener(e -> pnlListaVuelos.setVisible(true));


        //calendario
        pnlCalendario.add(dateChooserFechaVuelos);
        btnBuscarVuelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVuelos();
            }
        });
        btnMostrarCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla();
                bloquearCampos(true);
            }
        });
        btnSeleccionarVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vueloSeleccionado != null) {
                    verificarSeleccionDeVuelo();
                } else{
                    JOptionPane.showMessageDialog(null, "Seleccione un vuelo", "Aviso", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        tblCatalogoDeVuelos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tblCatalogoDeVuelos.getSelectedRow();
                if (fila != -1) {
                    vueloSeleccionado = new Vuelo(tblCatalogoDeVuelos.getValueAt(fila, 0).toString(),
                            tblCatalogoDeVuelos.getValueAt(fila, 1).toString(),
                            tblCatalogoDeVuelos.getValueAt(fila, 3).toString(),
                            tblCatalogoDeVuelos.getValueAt(fila, 2).toString(),
                            Integer.parseInt(tblCatalogoDeVuelos.getValueAt(fila, 4).toString()));
                }
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorReservasAsiento.contarReservasPendientes() >0) {
                    verificarExtracionReserva();
                }else {
                    JOptionPane.showMessageDialog(null, "No existe reservas pendientes", "Aviso", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        tblReservasPendientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tblReservasPendientes.getSelectedRow();
                if (fila != -1) {
                    reservaVueloSeleccionado = new Vuelo(tblReservasPendientes.getValueAt(fila, 1).toString(),
                            tblReservasPendientes.getValueAt(fila, 2).toString(),
                            tblReservasPendientes.getValueAt(fila, 3).toString(),
                            tblReservasPendientes.getValueAt(fila, 4).toString());
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorReservasAsiento.contarReservasPendientes() >0) {
                    verificarCancelacionReserva();
                }else {
                    JOptionPane.showMessageDialog(null, "No existe reservas pendientes", "Aviso", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }

    private void verificarCancelacionReserva() {
        if (reservaVueloSeleccionado != null) {
            CarritoAsientos carritoAsientos = new CarritoAsientos(reservaVueloSeleccionado);
            ReservaAsiento reserva = new ReservaAsiento(carritoAsientos, sesion.getUsuarioVerificado());
            gestorReservasAsiento.seleccionarReserva(reserva).cancelarReserva();
            gestorReservasAsiento.eliminarReserva(reserva);
            actualizarTablaCatalogo();
            JOptionPane.showMessageDialog(null, "Reserva cancelada con exito", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
            reservaVueloSeleccionado = null;
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una reserva", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarExtracionReserva() {
        if (reservaVueloSeleccionado != null) {
            CarritoAsientos carritoAsientos = new CarritoAsientos(reservaVueloSeleccionado);
            ReservaAsiento reserva = new ReservaAsiento(carritoAsientos, sesion.getUsuarioVerificado());
            pagoVuelos.iniciarDatos(gestorReservasAsiento.seleccionarReserva(reserva));
            mostrarPagoVuelos(ModuloVuelos.this);
            reservaVueloSeleccionado = null;
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una reserva", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarSeleccionDeVuelo() {
        if(VerificarReserva()) {
            selectorDeAsientos.setVuelo(gestorVuelos.seleccionarVuelo(vueloSeleccionado));
            mostrarPantallaEmergente(ModuloVuelos.this);
            vueloSeleccionado = null;
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un reserva con ese vuelo", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarVuelos() {
        boolean bandera = true;
        String fechaFormateada = "";
        obtenerFiltroBusquedaCorrespondiente(bandera, fechaFormateada);
    }

    private void obtenerFiltroBusquedaCorrespondiente(boolean bandera, String fechaFormateada) {
        if(dateChooserFechaVuelos.getDate() == null){
            bandera = false;
        }else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaFormateada = formato.format(dateChooserFechaVuelos.getDate());
        }
        if(!txtOrigen.getText().isEmpty() && !txtDestino.getText().isEmpty() && bandera){
            gestorVuelos.mostrarVuelosFiltrados(tblCatalogoDeVuelos, gestorVuelos.filtrarVuelo(txtOrigen.getText().toString(), txtDestino.getText().toString(), fechaFormateada));
        }else if(!txtOrigen.getText().isEmpty() && !txtDestino.getText().isEmpty()) {
            gestorVuelos.mostrarVuelosFiltrados(tblCatalogoDeVuelos, gestorVuelos.buscarVuelo(txtOrigen.getText(), txtDestino.getText()));
        } else if(bandera && txtOrigen.getText().isEmpty() &&  txtDestino.getText().isEmpty()){
            gestorVuelos.mostrarVuelosFiltrados(tblCatalogoDeVuelos, gestorVuelos.buscarVueloPorFecha(fechaFormateada));
        } else {
            JOptionPane.showMessageDialog(null, "Datos insuficientes para la busqueda", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarVuelosConDatosIniciales() {
        String fechaFormateada = "";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if(destinoComun != null) {
            fechaFormateada = formato.format(fechaComun);
            gestorVuelos.mostrarVuelosFiltrados(tblCatalogoDeVuelos, gestorVuelos.buscarVueloPorDestinoFecha(destinoComun, fechaFormateada));
            bloquearCampos(false);
        }else{
            MostrarTabla();
        }
    }

    private void bloquearCampos(boolean bandera) {
        btnBuscarVuelos.setEnabled(bandera);
        pnlCalendario.setEnabled(bandera);
        txtOrigen.setEnabled(bandera);
        txtDestino.setEnabled(bandera);
        dateChooserFechaVuelos.setEnabled(bandera);
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
        this.digVentanaSelectorAsiento = new JDialog(parentFrame, "Pantalla Emergente", true);

        digVentanaSelectorAsiento.add(selectorDeAsientos.pnlPrincipalAsientos);
        digVentanaSelectorAsiento.setSize(804, 604);
        digVentanaSelectorAsiento.setLocationRelativeTo(parentFrame);
        digVentanaSelectorAsiento.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        digVentanaSelectorAsiento.setVisible(true);
    }

    public void cerrarDialog(){
        digVentanaSelectorAsiento.dispose();
        actualizarTablaCatalogo();
    }


    public void MostrarTabla(){
        gestorVuelos.mostrarVuelos(tblCatalogoDeVuelos);
    }
    private void mostrarPagoVuelos(JFrame parentFrame) {
        this.digVentanaSelectorAsiento = new JDialog(parentFrame, "Pantalla Emergente", true);
        digVentanaSelectorAsiento.add(pagoVuelos.pnlPagoVuelos);
        digVentanaSelectorAsiento.setSize(804, 604);
        digVentanaSelectorAsiento.setLocationRelativeTo(parentFrame);
        digVentanaSelectorAsiento.setVisible(true);
    }

    public void actualizarTablaCatalogo(){
        gestorReservasAsiento.obtenerReservasPendientes(tblReservasPendientes);
        gestorReservasAsiento.mostrarReservas(tblHistorialReservas);
        if(destinoComun != null && !txtOrigen.isEnabled()) {
            String fechaFormateada = "";
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaFormateada = formato.format(fechaComun);
            gestorVuelos.mostrarVuelosFiltrados(tblCatalogoDeVuelos, gestorVuelos.buscarVueloPorDestinoFecha(destinoComun, fechaFormateada));
        }else {
            gestorVuelos.mostrarVuelos(tblCatalogoDeVuelos);
        }
    }


    public void crearReserva(CarritoAsientos lista) {
        ReservaAsiento reservaAsiento = new ReservaAsiento(new CarritoAsientos(lista.getAsientos(),lista.getVuelo()), sesion.getUsuarioVerificado());
        reservaAsiento.crearReserva();
        gestorReservasAsiento.añadirReserva(reservaAsiento);
    }

    public boolean VerificarReserva(){
        ComparadorVuelo comparadorVuelo = new ComparadorVuelo();
        for (int i = 0; i < tblReservasPendientes.getRowCount(); i++) {
            Vuelo vuelo = new Vuelo(tblReservasPendientes.getValueAt(i,1).toString(),
                    tblReservasPendientes.getValueAt(i,2).toString(),
                    tblReservasPendientes.getValueAt(i,3).toString(),
                    tblReservasPendientes.getValueAt(i,4).toString());
            if(comparadorVuelo.compare(vuelo, this.vueloSeleccionado) == 1 ){
                return false;
            }
        }
        return true;
    }
}

