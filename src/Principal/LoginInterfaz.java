package Principal;

import javax.swing.*;

public class LoginInterfaz extends JFrame {
    private JPanel pnlLogin;
    private JButton btnLogin;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JLabel lblContraseña;
    private JLabel lblUsuario;
    private JButton btnRegistrarse;
    private Login login;

    public LoginInterfaz() {
        login = new Login();
        btnLogin.addActionListener(e -> {
            String passwordIngresada = new String(txtContraseña.getPassword());
            if (login.validarUsuario(Integer.parseInt(txtUsuario.getText()), passwordIngresada)) {
                Módulos módulos = new Módulos(login);
                módulos.crearFrame();
                this.dispose();
            }
        });

        btnRegistrarse.addActionListener(e -> {
            RegistrarInterfaz registrarInterfaz = new RegistrarInterfaz(login);
            registrarInterfaz.crearFrame();
        });
    }

    public LoginInterfaz(Login log) {
        this.login = log;
        btnLogin.addActionListener(e -> {
            String passwordIngresada = new String(txtContraseña.getPassword());
            if (login.validarUsuario(Integer.parseInt(txtUsuario.getText()), passwordIngresada)) {
                Módulos módulos = new Módulos(login);
                módulos.crearFrame();
                this.dispose();
            }
        });

        btnRegistrarse.addActionListener(e -> {
            RegistrarInterfaz registrarInterfaz = new RegistrarInterfaz(login);
            registrarInterfaz.crearFrame();
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
