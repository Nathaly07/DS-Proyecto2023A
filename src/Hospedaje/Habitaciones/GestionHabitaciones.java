package Hospedaje.Habitaciones;

import Hospedaje.Reservas.ReservaHospedaje;

import java.io.*;
import java.util.*;

public class GestionHabitaciones {
    private List<Habitacion> habitaciones;

    public GestionHabitaciones() {
        /*this.habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(224, 190.00, 3, new Hotel("Hotel Los Angeles", "Calle Los Angeles 123", "Los Angeles", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(225, 220.00, 2, new Hotel("Hotel Las Vegas", "Avenida Las Vegas 456", "Las Vegas", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(226, 280.00, 4, new Hotel("Hotel Roma", "Calle Roma 789", "Roma", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(227, 260.00, 2, new Hotel("Hotel Ciudad del Vaticano", "Plaza del Vaticano 123", "Ciudad del Vaticano", 5), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(228, 230.00, 3, new Hotel("Hotel Los Angeles", "Calle Los Angeles 123", "Los Angeles", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(229, 300.00, 2, new Hotel("Hotel Las Vegas", "Avenida Las Vegas 456", "Las Vegas", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(230, 310.00, 4, new Hotel("Hotel Roma", "Calle Roma 789", "Roma", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(231, 340.00, 2, new Hotel("Hotel Ciudad del Vaticano", "Plaza del Vaticano 123", "Ciudad del Vaticano", 5), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(232, 180.00, 3, new Hotel("Hotel Los Angeles", "Calle Los Angeles 123", "Los Angeles", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(301, 180.00, 2, new Hotel("Hotel Barcelona", "Calle Barcelona 567", "Barcelona", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(302, 250.00, 3, new Hotel("Hotel Madrid", "Calle Madrid 789", "Madrid", 5), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(303, 320.00, 4, new Hotel("Hotel París", "Rue de Paris 123", "Paris", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(304, 270.00, 2, new Hotel("Hotel Berlín", "Unter den Linden 456", "Berlin", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(305, 290.00, 3, new Hotel("Hotel Ámsterdam", "Keizersgracht 789", "Amsterdam", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(306, 210.00, 2, new Hotel("Hotel Londres", "Baker Street 123", "Londres", 5), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(307, 350.00, 4, new Hotel("Hotel Venecia", "Canal Grande 456", "Venecia", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(308, 240.00, 2, new Hotel("Hotel Estambul", "Istiklal Caddesi 789", "Estambul", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(309, 280.00, 3, new Hotel("Hotel Praga", "Náměstí Republiky 123", "Praga", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(310, 190.00, 2, new Hotel("Hotel Budapest", "Andrássy út 456", "Budapest", 5), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(501, 70, 3, new Hotel("Hotel Cuenca", "Calle Cuenca 101", "Cuenca", 3), new CaracteristicasHabitacion[]{}));
        habitaciones.add(new Habitacion(502, 100.00, 5, new Hotel("Hotel Cuenca", "Calle Cuenca 101", "Cuenca", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(503, 60.00, 2, new Hotel("Hotel Cuenca", "Calle Cuenca 101", "Cuenca", 3), new CaracteristicasHabitacion[]{}));
        habitaciones.add(new Habitacion(504, 40.00, 1, new Hotel("Hotel Cuenca", "Calle Cuenca 101", "Cuenca", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(601, 160.00, 2, new Hotel("Hotel Manta", "Avenida Manta 101", "Manta", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS, CaracteristicasHabitacion.FUMADORES}));
        habitaciones.add(new Habitacion(602, 190.00, 3, new Hotel("Hotel Manta", "Avenida Manta 101", "Manta", 3), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(701, 210.00, 2, new Hotel("Hotel Roma", "Via Roma 101", "Roma", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.MASCOTAS}));
        habitaciones.add(new Habitacion(702, 250.00, 3, new Hotel("Hotel Roma", "Via Roma 101", "Roma", 4), new CaracteristicasHabitacion[]{CaracteristicasHabitacion.FUMADORES}));

        this.guardar();*/

        this.habitaciones = this.leer();
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
