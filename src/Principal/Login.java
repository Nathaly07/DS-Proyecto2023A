package Principal;

import javax.swing.*;

public class Login {
    private Usuario usuarioVerificado;
    private GestorUsuarios gestor;

    public Login() {
        this.gestor = new GestorUsuarios();
        this.gestor.insertarUsuarios();
    }

    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        boolean verificacion = false;
        Usuario usuario = this.gestor.buscarUsuario(nombreUsuario);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "¡Ese usuario no existe!", "ERROR 02", JOptionPane.ERROR_MESSAGE);
        } else {
            if (usuario.iniciarSesión(contraseña)) {
                this.usuarioVerificado = usuario;
                verificacion = true;
            } else {
                JOptionPane.showMessageDialog(null, "ERROR 401\nDatos erróneos. Comprueba las credenciales.");
            }
        }
        return verificacion;
    }

    public void registrarUsuario(Usuario usuario) {
        gestor.agregarUsuario(usuario);
    }

    public Usuario getUsuarioVerificado() {
        return this.usuarioVerificado;
    }

    public void setUsuarioVerificado(Usuario usr) {
        this.usuarioVerificado = usr;
    }
}
