import javax.swing.*;

public class Módulos extends JFrame {
    private JPanel pnlMódulos;
    private JButton btnMódulo1;
    private JButton btnMódulo2;
    private JButton btnMódulo3;
    private JButton btnMódulon;
    private JButton btnExit;

    public Módulos() {
        btnExit.addActionListener(e -> {
            Login login = new Login();
            login.crearFrame();
            dispose();
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
}
