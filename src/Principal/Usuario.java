package Principal;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    public Usuario(String nombreUsuario,String nombre, String apellido, String email, String password) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public boolean iniciarSesión(String password) {
        return password.equals(this.password);
    }


    public void actualizarDatos(String nombre, String apellido, String email, String password) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
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