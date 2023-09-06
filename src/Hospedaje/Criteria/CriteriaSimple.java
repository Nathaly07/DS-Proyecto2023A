package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.List;

public interface CriteriaSimple <T>{
    public List<T> meetCriteria(List<T> filtrados);
}
