package Principal;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginInterfaz extends JFrame {
    private JPanel pnlLogin;
    private JButton btnLogin;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JLabel lblContraseña;
    private JLabel lblUsuario;
    private JButton btnRegistrarse;
    private Sesion sesion;

    public LoginInterfaz() {
        sesion = Sesion.getInstance();

        btnLogin.addActionListener(e -> {
            String passwordIngresada = new String(txtContraseña.getPassword());
            if (sesion.validarUsuario(txtUsuario.getText(), passwordIngresada)) {
                Módulos módulos = new Módulos(sesion);
                módulos.crearFrame();
                this.dispose();
            }
        });

        btnRegistrarse.addActionListener(e -> {
            RegistrarInterfaz registrarInterfaz = new RegistrarInterfaz(sesion);
            registrarInterfaz.crearFrame();
        });
        txtContraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    btnLogin.doClick();
                }
            }
        });
    }

    public LoginInterfaz(Sesion log) {
        this.sesion = log;
        btnLogin.addActionListener(e -> {
            String passwordIngresada = new String(txtContraseña.getPassword());
            if (sesion.validarUsuario(txtUsuario.getText(), passwordIngresada)) {
                Módulos módulos = new Módulos(sesion);
                módulos.crearFrame();
                this.dispose();
            }
        });

        btnRegistrarse.addActionListener(e -> {
            RegistrarInterfaz registrarInterfaz = new RegistrarInterfaz(sesion);
            registrarInterfaz.crearFrame();
        });
    }

    public void crearFrame() {
        setTitle("Iniciar sesión");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlLogin);
        setVisible(true);
    }
}
