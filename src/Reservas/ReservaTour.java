package Reservas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import Principal.Login;
import Reservas.Reserva;
import Seguros.GestorSeguros;
import Tours.PagoReserva;
import Tours.Tour;
import Tours.GestorTour;


public class ReservaTour extends Reserva {
    private String fechaCreacion;
    private boolean estadoReserva;
    private int numeroPersonas;
    private boolean seguroActivo;
    private ArrayList<Tour> toursAgregados;
    private String fechaConfirmacionPago;
    private GestorTour gestorTour;
    private PagoReserva pagoReserva;
    private GestorSeguros gestorSeguros;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yy");

    public ReservaTour(int numeroPersonas, GestorTour gestorTour, PagoReserva pagoReserva, Login login, GestorSeguros gestorSeguros) {
        super(login);
        this.fechaCreacion = dateFormat.format(Calendar.getInstance().getTime());
        this.estadoReserva = false;
        this.numeroPersonas = numeroPersonas;
        // this.segurosActivos = this.getSegurosUsuarioActual;
        this.toursAgregados = new ArrayList<Tour>();
        this.fechaConfirmacionPago = "Sin confirmar";
        this.gestorTour = gestorTour;
        this.pagoReserva = pagoReserva;
        this.gestorSeguros = gestorSeguros;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public int getNumeroPersonas() {
        return this.numeroPersonas;
    }

    @Override
    public void cancelarReserva() {
        if (this.estadoReserva) {
            Date fechaActual = Calendar.getInstance().getTime();
            Date fechaAuxiliar;
            Date fechaMasProxima;
            long difEnMilis = Long.MAX_VALUE;
            long auxDifEnMilis = 0;

            for (Tour tour: this.toursAgregados) {
                fechaAuxiliar = tour.getFechaInicio();
                auxDifEnMilis = fechaAuxiliar.getTime() - fechaActual.getTime();
                if (auxDifEnMilis < difEnMilis) {
                    fechaMasProxima = fechaAuxiliar;
                    difEnMilis = auxDifEnMilis;
                }
            }
            long difEnDias = TimeUnit.DAYS.convert(difEnMilis, TimeUnit.MILLISECONDS);
            JOptionPane.showMessageDialog(null,
                    "Ha cancelado su reserva a " + difEnDias + " días del inicio del tour \n" +
                            "más próximo reservado",
                    "Reserva Tour",
                    JOptionPane.WARNING_MESSAGE);
            this.pagoReserva.calcularDevolucion((int)difEnDias);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Ha cancelado su reserva",
                    "Reserva Tour",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void modificarReserva() {}

    public void agregarTour(Tour tour) throws ParseException {
        boolean isAvailable = true;
        Date fechaCreacionReserva = dateFormat.parse(this.fechaCreacion);
        Date fechaInicioTour = dateFormat.parse(tour.getFechaInicio());
        long difEnMilis = fechaInicioTour.getTime() - fechaCreacionReserva.getTime();

        if (difEnMilis <= 0) {
            JOptionPane.showMessageDialog(null,
                    "No existen mínimo 30 días de diferencia respecto al inicio del tour\n" +
                            "No es posible agregar el Tour",
                    "Reserva Tour",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            long difEnDias = TimeUnit.DAYS.convert(difEnMilis, TimeUnit.MILLISECONDS);

            if (difEnDias < 30) {
                JOptionPane.showMessageDialog(null,
                        "No existen mínimo 30 días de diferencia respecto al inicio del tour\n" +
                                "No es posible agregar el Tour",
                        "Reserva Tour",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (tour.getDisponibilidad() >= this.numeroPersonas) {
                    this.toursAgregados.add(tour);
                    JOptionPane.showMessageDialog(null,
                            "El tour se ha agregado correctamente.",
                            "Reserva Tour",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El número de personas en la reserva excede la disponibilidad del tour\n" +
                                    "No es posible agregar el Tour",
                            "Reserva Tour",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    // -1 -> Reserva ya fue confirmada
    public int tiempoSinConfirmar() throws ParseException {
        if (!this.estadoReserva) {
            Date fechaCreacionReserva = dateFormat.parse(this.fechaCreacion);
            Date fechaActual = Calendar.getInstance().getTime();
            long difEnMilis = fechaActual.getTime() - fechaCreacionReserva.getTime();
            return (int) (TimeUnit.DAYS.convert(difEnMilis, TimeUnit.MILLISECONDS));
        } else {
            return -1;
        }
    }

    public void removerTourAgregado(Tour tour){
        this.toursAgregados.remove(tour);
    }

    public void confirmarReserva() {
        this.estadoReserva = true;
        this.pagoReserva.pagar(this.toursAgregados);
    }

    public void tieneSeguroActivo() {
        return "";
    }

}
