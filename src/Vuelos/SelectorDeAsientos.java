package Vuelos;

import Vuelos.Logica.Vuelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Vuelo v;

    public SelectorDeAsientos() {
        btnF1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnA1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnA1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
                botones(7);
            }
        });
        btnF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botones(8);
            }
        });
        btnF9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botones(9);
            }
        });
        btnF10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botones(10);
            }
        });
        fila.setVisible(false);
        habilitarBotones(false);
    }

    public void setVuelo(Vuelo v){
        this.v = v;
    }
    public void crearframe() {
        setTitle("Selector de asientos");
        setSize(804, 604);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void botones(int fila){
        habilitarBotones(true);
        List<Integer> lista = v.getFila(fila);
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
}
