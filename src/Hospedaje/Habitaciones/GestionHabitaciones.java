package Hospedaje.Habitaciones;

import Hospedaje.Reservas.ReservaHospedaje;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionHabitaciones {
    private List<Habitacion> habitaciones;

    public GestionHabitaciones() {
        this.habitaciones = leer();
        /*habitaciones = new ArrayList<>();
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
        habitaciones.add(new Habitacion(204, 220.00, 2, new Hotel("Hotel Quito", "Calle Principal 123", "Quito", 5)));
        habitaciones.add(new Habitacion(205, 180.00, 3, new Hotel("Hotel Guayaquil", "Avenida Secundaria 456", "Guayaquil", 4)));
        habitaciones.add(new Habitacion(206, 150.00, 2, new Hotel("Hotel Manta", "Calle Manta 789", "Manta", 3)));
        habitaciones.add(new Habitacion(207, 210.00, 4, new Hotel("Hotel Cuenca", "Avenida Cuenca 321", "Cuenca", 4)));
        habitaciones.add(new Habitacion(208, 200.00, 2, new Hotel("Hotel Quito", "Calle Principal 123", "Quito", 5)));
        habitaciones.add(new Habitacion(209, 190.00, 3, new Hotel("Hotel Guayaquil", "Avenida Secundaria 456", "Guayaquil", 4)));
        habitaciones.add(new Habitacion(210, 170.00, 2, new Hotel("Hotel Manta", "Calle Manta 789", "Manta", 3)));
        habitaciones.add(new Habitacion(211, 230.00, 4, new Hotel("Hotel Cuenca", "Avenida Cuenca 321", "Cuenca", 4)));
        habitaciones.add(new Habitacion(212, 240.00, 2, new Hotel("Hotel Quito", "Calle Principal 123", "Quito", 5)));
        habitaciones.add(new Habitacion(213, 250.00, 3, new Hotel("Hotel Guayaquil", "Avenida Secundaria 456", "Guayaquil", 4)));
        habitaciones.add(new Habitacion(214, 180.00, 2, new Hotel("Hotel Manta", "Calle Manta 789", "Manta", 3)));
        habitaciones.add(new Habitacion(215, 270.00, 4, new Hotel("Hotel Cuenca", "Avenida Cuenca 321", "Cuenca", 4)));
        habitaciones.add(new Habitacion(216, 230.00, 2, new Hotel("Hotel Quito", "Calle Principal 123", "Quito", 5)));
        habitaciones.add(new Habitacion(217, 260.00, 3, new Hotel("Hotel Guayaquil", "Avenida Secundaria 456", "Guayaquil", 4)));
        habitaciones.add(new Habitacion(218, 140.00, 2, new Hotel("Hotel Manta", "Calle Manta 789", "Manta", 3)));
        habitaciones.add(new Habitacion(219, 290.00, 4, new Hotel("Hotel Cuenca", "Avenida Cuenca 321", "Cuenca", 4)));
        habitaciones.add(new Habitacion(220, 180.00, 2, new Hotel("Hotel Quito", "Calle Principal 123", "Quito", 5)));
        habitaciones.add(new Habitacion(221, 310.00, 3, new Hotel("Hotel Guayaquil", "Avenida Secundaria 456", "Guayaquil", 4)));
        habitaciones.add(new Habitacion(222, 120.00, 2, new Hotel("Hotel Manta", "Calle Manta 789", "Manta", 3)));
        habitaciones.add(new Habitacion(223, 330.00, 4, new Hotel("Hotel Cuenca", "Avenida Cuenca 321", "Cuenca", 4)));
        this.guardar();
        */
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

    private void guardar() {
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath.concat("/src/Hospedaje/data/habitaciones.txt");
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this.habitaciones);
            outputStream.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private List<Habitacion> leer() {
        List<Habitacion> habitacionesGuardadas = null;
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath.concat("/src/Hospedaje/data/habitaciones.txt");
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            habitacionesGuardadas = (List<Habitacion>) inputStream.readObject();
            inputStream.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }

        return habitacionesGuardadas;
    }

}
