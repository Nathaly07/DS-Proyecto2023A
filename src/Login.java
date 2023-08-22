import javax.swing.*;

public class Login extends JFrame {

    private JPanel pnlLogin;
    private JButton btnLogin;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JLabel lblContraseña;
    private JLabel lblUsuario;

    public Login() {

        btnLogin.addActionListener(e -> {
            String username = "a";
            String password = "a";
            String passwordIngresada = new String(txtContraseña.getPassword());

            if (txtUsuario.getText().equals(username) && passwordIngresada.equals(password)) {
                Módulos módulos = new Módulos();
                módulos.crearFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
            }
        });
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlLogin);
        setVisible(true);
    }
}
