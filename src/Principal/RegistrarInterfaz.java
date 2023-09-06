package Principal;

import javax.swing.*;

public class RegistrarInterfaz extends JFrame {
    private JPanel pnlRegistrarse;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JPasswordField txtContraseña;
    private JTextField txtRol;
    private JLabel lblIDUsuario;
    private JButton btnRegistrarse;
    private Login login;

    public RegistrarInterfaz(Login login) {
        this.login = login;
        btnRegistrarse.addActionListener(e -> {
            String contraseñaRegistro = new String(txtContraseña.getPassword());
            Usuario usuario = new Usuario(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtEmail.getText(),
                    contraseñaRegistro);
            lblIDUsuario.setText(String.valueOf(usuario.getID_Usuario()));
            login.registrarUsuario(usuario);

            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito. Su usuario es: " + lblIDUsuario.getText());
            dispose();
        });
    }

    public void crearFrame() {
        setSize(500, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlRegistrarse);
        setVisible(true);
    }
}
