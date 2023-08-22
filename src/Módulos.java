import Seguros.InterfazSeguros;

import javax.swing.*;
import java.awt.*;

public class Módulos extends JFrame {
    private JPanel pnlMódulos;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton btnSeguros;
    private JButton btnExit;
    private JPanel pnlContenido;


    public Módulos() {
        btnExit.addActionListener(e -> {
            Login login = new Login();
            login.crearFrame();
            dispose();
        });
        btnSeguros.addActionListener(e -> {
            InterfazSeguros interfazSeguros = new InterfazSeguros();
            setPanel(interfazSeguros.pnlOpcionesSeguro);
            crearFrame();
        });
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlMódulos);
        setVisible(true);
    }

    public void setPanel(JPanel pnlMódulos) {
        pnlContenido.add(pnlMódulos);
    }
}
