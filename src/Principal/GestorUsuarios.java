package Principal;

import Hospedaje.Reservas.ReservaHospedaje;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GestorUsuarios {

    private ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = this.leer();
    }

    public void agregarUsuario(Usuario usr) {
        usuarios.add(usr);
        this.guardar();
    }

    public void eliminarUsuario(Usuario usr) {
        usuarios.remove(usr);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        Usuario usuarioBuscado = null;
        for (Usuario usuario1 : usuarios) {
            if (usuario1.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                usuarioBuscado = usuario1;
                break;
            }
        }
        return usuarioBuscado;
    }

    private void guardar() {
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath.concat("/src/Principal/usuarios.txt");
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this.usuarios);
            outputStream.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private ArrayList<Usuario> leer() {
        ArrayList<Usuario> reservasGuardadas = null;
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath.concat("/src/Principal/usuarios.txt");
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            reservasGuardadas = (ArrayList<Usuario>) inputStream.readObject();
            inputStream.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }

        return reservasGuardadas;
    }

}
