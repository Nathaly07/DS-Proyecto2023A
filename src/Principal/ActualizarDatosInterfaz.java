package Principal;

import javax.swing.*;

public class ActualizarDatosInterfaz extends JFrame {
    public JPanel pnlActualizarDatos;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JTextField txtContraseña;
    private JRadioButton rbtnNombre;
    private JRadioButton rbtnApellido;
    private JRadioButton rbtnEmail;
    private JRadioButton rbtnContraseña;
    private JButton btnActualizar;
    private Login login;

    public ActualizarDatosInterfaz(Login login) {
        this.login = login;
        btnActualizar.addActionListener(e -> {
            String nombre = "";
            String apellido = "";
            String email = "";
            String contraseña = "";
            if (this.rbtnNombre.isSelected()) {
                txtNombre.setVisible(true);
                nombre = this.txtNombre.getText();
            } else {
                nombre = login.getUsuarioVerificado().getNombre();
            }
            if (this.rbtnApellido.isSelected()) {
                txtApellido.setVisible(true);
                apellido = this.txtApellido.getText();
            } else {
                apellido = login.getUsuarioVerificado().getApellido();
            }
            if (this.rbtnEmail.isSelected()) {
                txtEmail.setVisible(true);
                email = this.txtEmail.getText();
            } else {
                email = login.getUsuarioVerificado().getEmail();
            }
            if (this.rbtnContraseña.isSelected()) {
                txtContraseña.setVisible(true);
                contraseña = this.txtContraseña.getText();
            } else {
                contraseña = login.getUsuarioVerificado().getPassword();
            }
            login.getUsuarioVerificado().actualizarDatos(nombre, apellido, email, contraseña);
            JOptionPane.showMessageDialog(null, "Datos actualizados con exito.");
        });
    }


}
