package Reservas;

public abstract class Reserva {

    protected String usuarioID;
    protected String reservaID;

    public Reserva(String usuarioID, String reservaID) {
        this.usuarioID = usuarioID;
        this.reservaID = reservaID;
    }

    public abstract void cancelarReserva();
    public abstract void modificarReserva();

    // Esto deberia devolver el objeto del usuario logueado actualmente no un INT
    // Modificar cuando la clase Usuario sea creada y usada junto al login
    public int buscarUsuarioActual() {
        return 0;
    }

    public String getReservaID() {
        return reservaID;
    }

    public void setReservaID(String reservaID) {
        this.reservaID = reservaID;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

}
