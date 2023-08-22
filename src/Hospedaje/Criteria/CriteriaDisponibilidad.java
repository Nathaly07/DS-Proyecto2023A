package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaDisponibilidad implements Criteria {
    private boolean disponibilidad;

    public CriteriaDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.estaDisponible() == disponibilidad) {
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }
}
