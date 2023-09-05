package Reservas;

import Principal.Login;
import Principal.Usuario;

public abstract class Reserva {

    Login login;

    public Reserva(Login login) {
        this.login = login;
    }
    public abstract void cancelarReserva();
    public abstract void modificarReserva();

    public Usuario buscarUsuarioActual() {
        return this.login.getUsuarioVerificado();
    }

}