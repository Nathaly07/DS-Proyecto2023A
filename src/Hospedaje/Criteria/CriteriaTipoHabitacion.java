package Hospedaje.Criteria;

import Hospedaje.Habitaciones.CaracteristicasHabitacion;
import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaTipoHabitacion implements CriteriaSimple<Habitacion> {
    private CaracteristicasHabitacion caracteristica;

    public CriteriaTipoHabitacion(CaracteristicasHabitacion caracteristica) {
        this.caracteristica = caracteristica;
    }

    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones) {
        ArrayList<Habitacion> habitacionesFiltradas = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            CaracteristicasHabitacion[] caracteristicas = habitacion.getCaracteristicas();

            for (CaracteristicasHabitacion caracteristicaActual : caracteristicas) {
                if (caracteristicaActual.equals(this.caracteristica)) {
                    habitacionesFiltradas.add(habitacion);
                }
            }
        }

        return habitacionesFiltradas;
    }
}
