package Principal;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int ID_Usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

    public Usuario(String nombre, String apellido, String email, String password) {
        this.ID_Usuario = (int) (Math.random() * 5000 + 1000);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public boolean iniciarSesión(String password) {
        return password.equals(this.password);
    }


    public void actualizarDatos(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public int getID_Usuario() {
        return this.ID_Usuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
