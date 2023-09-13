package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaTipoHabitacion implements CriteriaSimple<Habitacion>{
    private String tipoHabitacion;

    public CriteriaTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones) {
        ArrayList<Habitacion> habitacionesFiltradas = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                habitacionesFiltradas.add(habitacion);
            }
        }

        return habitacionesFiltradas;
    }
}
