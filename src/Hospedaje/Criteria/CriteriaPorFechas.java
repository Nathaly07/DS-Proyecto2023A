package Hospedaje.Criteria;

import Hospedaje.Habitaciones.Habitacion;
import Hospedaje.Reservas.ReservaHospedaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CriteriaPorFechas implements CriteriaSimple<ReservaHospedaje>{
    private Date reservarDesde;
    private Date reservarHasta;

    public CriteriaPorFechas(Date reservarDesde, Date reservarHasta) {
        this.reservarDesde = reservarDesde;
        this.reservarHasta = reservarHasta;
    }

    @Override
    public List<ReservaHospedaje> meetCriteria(List<ReservaHospedaje> reservas) {
        List<ReservaHospedaje> reservasFiltradas = new ArrayList<ReservaHospedaje>();

        for (ReservaHospedaje reserva : reservas) {
            Date fechaInicioReserva = reserva.getFechaInicio();
            Date fechaFinReserva = reserva.getFechaFin();

            // Si la reserva coincide con el rango de fechas solicitado, eliminamos todas las habitaciones de esa reserva de la lista de disponibles
            if (!(fechaInicioReserva.after(reservarHasta) || fechaFinReserva.before(reservarDesde))) {
                reservasFiltradas.add(reserva);
            }
        }

        return reservasFiltradas;
    }
}
