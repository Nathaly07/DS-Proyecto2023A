package Hospedaje.Habitaciones;

public enum TipoHabitacion {
    MASCOTAS("mascotas"),
    FUMADOR("fumador"),
    ;

    public final String tipo;
    private TipoHabitacion(String tipo) {
        this.tipo = tipo;
    }
}
