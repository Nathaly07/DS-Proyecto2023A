package Principal;

import javax.swing.*;

public class Login {
    private Usuario usuarioVerificado;
    private GestorUsuarios gestor;

    public Login() {
        this.gestor = new GestorUsuarios();
    }

    public boolean validarUsuario(int ID_Usuario, String contraseña) {
        boolean verificacion = false;
        Usuario usuario = this.gestor.buscarUsuario(ID_Usuario);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Ese usuario no existe.");
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
