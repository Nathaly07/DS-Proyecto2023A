package Principal;

import java.util.ArrayList;

public class GestorUsuarios {

    private ArrayList<Usuario> usuario;

    public GestorUsuarios() {
        usuario = new ArrayList<Usuario>();
    }

    public void agregarUsuario(Usuario usr) {
        usuario.add(usr);
    }

    public void eliminarUsuario(Usuario usr) {
        usuario.remove(usr);
    }

    public Usuario buscarUsuario(int ID) {
        Usuario usuarioBuscado = null;
        for (Usuario usuario1 : usuario) {
            if (usuario1.getID_Usuario() == ID) {
                usuarioBuscado = usuario1;
                break;
            }
        }
        return usuarioBuscado;
    }

}
