package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.List;

public interface HabitacionesCriteria {
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones);
}
