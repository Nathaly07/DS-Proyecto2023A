package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaCiudadHabitaciones implements HabitacionesCriteria {
    private String ciudad;

    public CriteriaCiudadHabitaciones(String ciudad) {
        this.ciudad = ciudad;
    }
    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones) {
        List<Habitacion> habitacionesCiudad = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getCiudad().equals(ciudad)) {
                habitacionesCiudad.add(habitacion);
            }
        }

        return habitacionesCiudad;
    };
}
