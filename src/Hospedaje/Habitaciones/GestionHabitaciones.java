package Hospedaje.Habitaciones;

import java.util.ArrayList;
import java.util.List;

public class GestionHabitaciones {
    private List<Habitacion> habitaciones;

    public GestionHabitaciones() {
        habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(101, 150.00, 2,  new Hotel("Hotel Quito", "Calle Principal 123", "Quito", 5)));
        habitaciones.add(new Habitacion(102, 200.00, 3,  new Hotel("Hotel Guayaquil", "Avenida Secundaria 456", "Guayaquil", 4)));
        habitaciones.add(new Habitacion(103, 120.00, 2,  new Hotel("Hotel Manta", "Calle Manta 789", "Manta", 3)));
        habitaciones.add(new Habitacion(104, 180.00, 4,  new Hotel("Hotel Cuenca", "Avenida Cuenca 321", "Cuenca", 4)));
        habitaciones.add(new Habitacion(105, 130.00, 2,  new Hotel("Hotel Ibarra", "Calle Ibarra 654", "Ibarra", 3)));
        habitaciones.add(new Habitacion(106, 210.00, 3,  new Hotel("Hotel Loja", "Avenida Loja 987", "Loja", 4)));
        habitaciones.add(new Habitacion(107, 160.00, 2,  new Hotel("Hotel Ambato", "Calle Ambato 135", "Ambato", 3)));
        habitaciones.add(new Habitacion(108, 190.00, 4,  new Hotel("Hotel Riobamba", "Avenida Riobamba 246", "Riobamba", 4)));
        habitaciones.add(new Habitacion(109, 140.00, 2,  new Hotel("Hotel Esmeraldas", "Calle Esmeraldas 369", "Esmeraldas", 3)));
        habitaciones.add(new Habitacion(110, 220.00, 3,  new Hotel("Hotel Baños", "Avenida Baños 482", "Baños", 4)));
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Habitacion getHabitacionById(int id){
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getHabitacionID() == id) {
                return habitacion;
            }
        }
        return null;
    }

}
