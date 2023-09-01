package Vuelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagoVuelos extends JFrame {


    public JPanel JPPagoVuelos;
    private JButton cerrarButton;
    private JButton pagarButton;

    public PagoVuelos() {
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }

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
