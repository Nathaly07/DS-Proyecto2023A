package Vuelos.GUI;

import Vuelos.Logica.Asiento;
import Vuelos.Logica.CarritoAsientos;
import Vuelos.Logica.Vuelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectorDeAsientos extends JFrame{
    public JPanel pnlPrincipalAsientos;
    private JButton btnF10;
    private JButton btnF9;
    private JButton btnF8;
    private JButton btnF7;
    private JButton btnF6;
    private JButton btnF5;
    private JButton btnF4;
    private JButton btnF3;
    private JButton btnF2;
    private JButton btnF1;
    private JButton btnA1;
    private JButton btnA2;
    private JButton btnA3;
    private JButton btnA4;
    private JButton btnA5;
    private JButton btnA6;
    private JButton btnConfirmarSeleccion;
    private JButton btnCancelar;
    private JScrollPane scrAsientos;
    private JPanel pnlContenidoAsientos;
    private JPanel pnlImagenAsientos;
    private JPanel pnlClasesAsientos;
    private JPanel pnlSeleccionAsientos;
    private JPanel pnlConfirmacionAsientos;
    private JPanel pnlTuristaAsientos;
    private JPanel pnlVIPAsientos;
    private JPanel pnlTextoTurista;
    private JPanel pnlBotonesTurista;
    private JLabel lblFila;
    private JTable tblCarritoAsiento;
    private JButton btnEliminarRegistro;
    private Vuelo vuelo;
    private Asiento asiento;
    private ModuloVuelos moduloVuelos;

    private CarritoAsientos carrito ;

    public SelectorDeAsientos(ModuloVuelos moduloVuelos) {
        this.moduloVuelos = moduloVuelos;

        btnF1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("1");
                botones(1);
            }
        });
        btnF2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("2");
                botones(2);
            }
        });
        btnF3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("3");
                botones(3);
            }
        });
        btnF4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("4");
                botones(4);
            }
        });
        btnF5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("5");
                botones(5);
            }
        });
        btnF6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("6");
                botones(6);
            }
        });
        btnF7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("7");
                botones(7);
            }
        });
        btnF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("8");
                botones(8);
            }
        });
        btnF9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("9");
                botones(9);
            }
        });
        btnF10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFila.setText("10");
                botones(10);
            }
        });
        lblFila.setVisible(false);
        habilitarBotones(false);
        btnA1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla(1);
                botones(Integer.parseInt(lblFila.getText()));
            }
        });
        btnA2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla(2);
                botones(Integer.parseInt(lblFila.getText()));

            }
        });
        btnA3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla(3);
                botones(Integer.parseInt(lblFila.getText()));
            }
        });
        btnA4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla(4);
                botones(Integer.parseInt(lblFila.getText()));

            }
        });
        btnA5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla(5);
                botones(Integer.parseInt(lblFila.getText()));
            }
        });
        btnA6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla(6);
                botones(Integer.parseInt(lblFila.getText()));
            }
        });
        btnEliminarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(asiento != null) {
                    carrito.eliminarAsiento(asiento);
                    carrito.mostrarCarrito(tblCarritoAsiento);
                    botones(Integer.parseInt(lblFila.getText()));
                } else{
                    JOptionPane.showMessageDialog(null, "Seleccione un asiento", "Aviso", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tblCarritoAsiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tblCarritoAsiento.getSelectedRow();
                if (fila != -1) {
                    asiento = new Asiento(Integer.parseInt(tblCarritoAsiento.getValueAt(fila, 0).toString()),
                            Integer.parseInt(tblCarritoAsiento.getValueAt(fila, 1).toString())
                    );
                }
            }
        });
        btnConfirmarSeleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoAsientos carritoAsientos = carrito;
                if(carritoAsientos.getAsientos().size() > 0){
                    moduloVuelos.crearReserva(carritoAsientos);
                    moduloVuelos.actualizarTablaCatalogo();
                    habilitarBotones(false);
                    carrito.mostrarCarrito(tblCarritoAsiento);
                }
                moduloVuelos.cerrarDialog();

            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carrito.limpiarCarrito();
                habilitarBotones(false);
                carrito.mostrarCarrito(tblCarritoAsiento);
                moduloVuelos.cerrarDialog();
            }
        });
    }

    public void setVuelo(Vuelo vuelo){
        this.vuelo = vuelo;
        carrito = new CarritoAsientos(vuelo);
        carrito.limpiarCarrito();
        carrito.mostrarCarrito(tblCarritoAsiento);

    }
    public void crearframe() {
        setTitle("Selector de asientos");
        setSize(804, 604);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void botones(int fila){
        habilitarBotones(true);
        List<Integer> lista = vuelo.getFila(fila);
        lista.addAll(asientosSeleccionables(fila));
        for (int i: lista) {
            switch (i) {
                case 1:
                    btnA1.setEnabled(false);
                    break;
                case 2:
                    btnA2.setEnabled(false);
                    break;
                case 3:
                    btnA3.setEnabled(false);
                    break;
                case 4:
                    btnA4.setEnabled(false);
                    break;
                case 5:
                    btnA5.setEnabled(false);
                    break;
                case 6:
                    btnA6.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    }
    public void habilitarBotones(boolean bandera){
        btnA1.setEnabled(bandera);
        btnA2.setEnabled(bandera);
        btnA3.setEnabled(bandera);
        btnA4.setEnabled(bandera);
        btnA5.setEnabled(bandera);
        btnA6.setEnabled(bandera);
    }
    public void mostrarTabla(int numAsiento){
        carrito.añadirAsiento(vuelo.buscarAsiento(new Asiento(numAsiento,Integer.parseInt(lblFila.getText()))));
        carrito.mostrarCarrito(tblCarritoAsiento);
    }
    public List<Integer> asientosSeleccionables(int fila){
        List<Integer> asientosHabilitados = new ArrayList<>();
        for (int i = 0; i < tblCarritoAsiento.getRowCount(); i++) {
            if(Integer.parseInt(tblCarritoAsiento.getValueAt(i,1).toString()) == fila){
                asientosHabilitados.add(Integer.parseInt(tblCarritoAsiento.getValueAt(i,0).toString()));
            }
        }
        return asientosHabilitados;
    }

}
