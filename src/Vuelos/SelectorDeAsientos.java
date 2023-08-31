package Vuelos;

import Vuelos.Logica.Vuelo;

import javax.swing.*;

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
    private JTextPane seleccionarFilaTextPane;
    private JTextPane claseVIPTextPane1;
    private Vuelo v;

    public void SelectorDeAsientos(){

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
}
