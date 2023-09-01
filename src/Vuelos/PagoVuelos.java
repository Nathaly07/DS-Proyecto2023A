package Vuelos;

import javax.swing.*;

public class PagoVuelos extends JFrame {


    private JPanel JPPagoVuelos;
    private JButton cerrarButton;
    private JButton pagarButton;

    public void crearframe() {
        setTitle("Confirmar Reserva");
        setContentPane(JPPagoVuelos);
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
