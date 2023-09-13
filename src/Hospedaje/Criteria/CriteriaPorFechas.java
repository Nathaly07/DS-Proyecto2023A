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

            // Si la fecha de inicio de la reserva es posterior a la fecha de fin de la reserva que se quiere hacer
            // o si la fecha de fin de la reserva es anterior a la fecha de inicio de la reserva que se quiere hacer
            // entonces la reserva no se puede hacer

            // El siguiente rango de fechas verifica que se devuelvan las reservas que no se pueden hacer
            if (!(fechaInicioReserva.after(reservarHasta) || fechaFinReserva.before(reservarDesde))) {
                reservasFiltradas.add(reserva);
            }
        }

        return reservasFiltradas;
    }
}
