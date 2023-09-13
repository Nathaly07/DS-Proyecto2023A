package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaPorPersonas implements CriteriaSimple<Habitacion>{
    private int personas;

    public CriteriaPorPersonas(int personas) {
        this.personas = personas;
    }


    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> filtrados) {
        ArrayList<Habitacion> habitacionesFiltradas = new ArrayList<>();

        for (Habitacion habitacion : filtrados) {
            if (this.personas <= habitacion.getLimiteUsuarios()) {
                habitacionesFiltradas.add(habitacion);
            }
        }

        return habitacionesFiltradas;
    }
}
