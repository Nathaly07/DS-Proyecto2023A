package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.List;

public interface Criteria {
    List<Habitacion> meetCriteria(List<Habitacion> habitaciones);
}
