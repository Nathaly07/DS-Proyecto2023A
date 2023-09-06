package Vuelos;

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
    private JButton confirmarSelecciónButton;
    private JButton cancelarButton;
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
    private JLabel fila;
    private JTable table1;
    private JButton btnEliminarRegistro;
    private Vuelo v;
    private Asiento a;
    private ModuloVuelos moduloVuelos;

    private CarritoAsientos carrito ;

    public SelectorDeAsientos(ModuloVuelos moduloVuelos) {
        this.moduloVuelos = moduloVuelos;

        btnF1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("1");
                botones(1);
            }
        });
        btnF2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("2");
                botones(2);
            }
        });
        btnF3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("3");
                botones(3);
            }
        });
        btnF4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("4");
                botones(4);
            }
        });
        btnF5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("5");
                botones(5);
            }
        });
        btnF6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("6");
                botones(6);
            }
        });
        btnF7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("7");
                botones(7);
            }
        });
        btnF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("8");
                botones(8);
            }
        });
        btnF9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("9");
                botones(9);
            }
        });
        btnF10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.setText("10");
                botones(10);
            }
        });
        fila.setVisible(false);
        habilitarBotones(false);
        btnA1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla(1);
                botones(Integer.parseInt(fila.getText()));
            }
        });
        btnA2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla(2);
                botones(Integer.parseInt(fila.getText()));

            }
        });
        btnA3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla(3);
                botones(Integer.parseInt(fila.getText()));
            }
        });
        btnA4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla(4);
                botones(Integer.parseInt(fila.getText()));

            }
        });
        btnA5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla(5);
                botones(Integer.parseInt(fila.getText()));
            }
        });
        btnA6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarTabla(6);
                botones(Integer.parseInt(fila.getText()));
            }
        });
        btnEliminarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a != null) {
                    carrito.eliminar(a);
                    carrito.mostarCarrito(table1);
                    botones(Integer.parseInt(fila.getText()));
                } else{
                    JOptionPane.showMessageDialog(null, "Seleccione un asiento", "Aviso", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table1.getSelectedRow();
                if (fila != -1) {
                    a = new Asiento(Integer.parseInt(table1.getValueAt(fila, 0).toString()),
                            Integer.parseInt(table1.getValueAt(fila, 1).toString())
                    );
                }
            }
        });
        confirmarSelecciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoAsientos aux = carrito;
                moduloVuelos.crearReserva(aux);
                moduloVuelos.actualizar();
                habilitarBotones(false);
                carrito.mostarCarrito(table1);
                moduloVuelos.cerrarDialog();

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carrito.Limpiar();
                habilitarBotones(false);
                carrito.mostarCarrito(table1);
                moduloVuelos.cerrarDialog();
            }
        });
    }

    public void setVuelo(Vuelo v){
        this.v = v;
        carrito = new CarritoAsientos(v);
        carrito.Limpiar();
        carrito.mostarCarrito(table1);

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
        List<Integer> lista = v.getFila(fila);
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
    public void MostrarTabla(int numAsiento){
        carrito.añadir(v.seleccionarAsiento(new Asiento(numAsiento,Integer.parseInt(fila.getText()))));
        carrito.mostarCarrito(table1);
    }
    public List<Integer> asientosSeleccionables(int fila){
        List<Integer> asientosHabilitados = new ArrayList<>();
        for (int i = 0; i < table1.getRowCount(); i++) {
            if(Integer.parseInt(table1.getValueAt(i,1).toString()) == fila){
                asientosHabilitados.add(Integer.parseInt(table1.getValueAt(i,0).toString()));
            }
        }
        return asientosHabilitados;
    }

}
