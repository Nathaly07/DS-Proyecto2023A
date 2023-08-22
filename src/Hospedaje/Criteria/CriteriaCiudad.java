package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class CriteriaCiudad implements Criteria {
    private String ciudad;

    public CriteriaCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public List<Habitacion> meetCriteria(List<Habitacion> habitaciones) {
        List<Habitacion> habitacionesPorCiudad = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getHotel().getCiudad().equalsIgnoreCase(ciudad)) {
                habitacionesPorCiudad.add(habitacion);
            }
        }
        return habitacionesPorCiudad;
    }
}