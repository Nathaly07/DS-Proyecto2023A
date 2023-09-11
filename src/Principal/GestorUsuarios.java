package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    public Usuario buscarUsuario(String nombreUsuario) {
        Usuario usuarioBuscado = null;
        for (Usuario usuario1 : usuario) {
            if (usuario1.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                usuarioBuscado = usuario1;
                break;
            }
        }
        return usuarioBuscado;
    }

    public void insertarUsuarios() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("src/Principal/Usuarios.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                StringTokenizer registro = new StringTokenizer(linea, "\n");
                while (registro.hasMoreTokens()) {
                    StringTokenizer subCadena = new StringTokenizer(registro.nextToken(), ",");
                    this.agregarUsuario(new Usuario(subCadena.nextToken(), subCadena.nextToken(), subCadena.nextToken(), subCadena.nextToken(), subCadena.nextToken()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
