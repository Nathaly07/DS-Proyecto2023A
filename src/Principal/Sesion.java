package Principal;

import javax.swing.*;

public class Sesion {
    private static Sesion instance;
    private Usuario usuarioVerificado;
    private GestorUsuarios gestor;
    private String fechaComun = null;
    private String destinoComun = null;

    private Sesion() {
        this.gestor = new GestorUsuarios();
        this.gestor.insertarUsuarios();
    }

    public static Sesion getInstance() {
        if (instance == null) {
            instance = new Sesion();
        }
        return instance;
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

    public String getFechaComun() {
        return fechaComun;
    }

    public String getDestinoComun() {
        return destinoComun;
    }

    public void setFechaComun(String fechaComun) {
        this.fechaComun = fechaComun;
    }

    public void setDestinoComun(String destinoComun) {
        this.destinoComun = destinoComun;
    }
}
