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
    private JTextField txtUser;
    private JButton btnCancelar;
    private Sesion sesion;

    public RegistrarInterfaz(Sesion sesion) {
        this.sesion = sesion;
        btnRegistrarse.addActionListener(e -> {
            String password = new String(txtContraseña.getPassword());
            String user = txtUser.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String email = txtEmail.getText();
            if((password.equalsIgnoreCase(""))||(user.equalsIgnoreCase(""))||(nombre.equalsIgnoreCase(""))||(apellido.equalsIgnoreCase(""))||(email.equalsIgnoreCase(""))){
                JOptionPane.showMessageDialog(null, "¡No has llenado todos los campos!", "ERROR 01", JOptionPane.ERROR_MESSAGE);
            }else {
                Usuario usuario = new Usuario(txtUser.getText(),
                        txtNombre.getText(),
                        txtApellido.getText(),
                        txtEmail.getText(),
                        password);
                sesion.registrarUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.", "Gracias", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }


        });

        btnCancelar.addActionListener(e -> {
            this.dispose();
        });
    }

    public void crearFrame() {
        setTitle("Registro de usuario");
        setSize(500, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlRegistrarse);
        setVisible(true);
    }
}
