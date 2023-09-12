package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaCiudad implements CriteriaSimple<Habitacion> {
    private String ciudad;

    public CriteriaCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones) {
        List<Habitacion> habitacionesPorCiudad = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getCiudad().equals(ciudad)) {
                habitacionesPorCiudad.add(habitacion);
            }
        }

        return habitacionesPorCiudad;
    };
}
