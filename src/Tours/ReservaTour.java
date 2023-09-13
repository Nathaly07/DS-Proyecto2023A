package Tours;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import java.util.concurrent.TimeUnit;


public class ReservaTour {
    private static int cantidadReservas = 0;
    
    private String nombreUsuario;
    private String apellidoUsuario;

    private int numReserva;
    private String fechaCreacion = "-";
    private boolean estadoReserva;
    private int numeroPersonas;
    private ArrayList<Tour> toursAgregados;
    private String fechaConfirmacionPago = "-";

    private GestorTour gestorTour;
    private PagoReserva pagoReserva;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    public ReservaTour(String nombreUsuario, String apellidoUsuario, int numeroPersonas, GestorTour gestorTour, PagoReserva pagoReserva) {
        this.numReserva = this.cantidadReservas;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.fechaCreacion = dateFormat.format(Calendar.getInstance().getTime());
        this.estadoReserva = false;
        this.numeroPersonas = numeroPersonas;
        this.toursAgregados = new ArrayList<Tour>();
        this.fechaConfirmacionPago = "Sin confirmar";
        this.gestorTour = gestorTour;
        this.pagoReserva = pagoReserva;
        this.cantidadReservas += 1;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public int getNumReserva() {
        return this.numReserva;
    }

    public String getFechaCreacion() {
        return this.fechaCreacion;
    }

    public String getFechaConfirmacionPago() {
        return this.fechaConfirmacionPago;
    }

    public int getNumeroPersonas() {
        return this.numeroPersonas;
    }
    public java.lang.String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public String getApellidoUsuario() {
        return this.apellidoUsuario;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setToursAgregados(ArrayList<Tour> toursAgregados) {
        this.toursAgregados = toursAgregados;
    }

    public ArrayList<Tour> getToursAgregados() {
        return this.toursAgregados;
    }

    public void cancelarReserva() {
        if (this.estadoReserva) {
            Date fechaActual = Calendar.getInstance().getTime();
            Date fechaAuxiliar;
            Date fechaMasProxima;
            long difEnMilis = Long.MAX_VALUE;
            long auxDifEnMilis = 0;

            for (Tour tour: this.toursAgregados) {
                try {
                    fechaAuxiliar = this.dateFormat.parse(tour.getFechaInicio());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
            this.pagoReserva.calcularDevolucion((int)difEnDias, this.toursAgregados);
            for (Tour tour: this.toursAgregados) {

            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Ha cancelado su reserva",
                    "Reserva Tour",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

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

    public void confirmarReserva(String metodoPago) {
        this.estadoReserva = true;
        this.fechaConfirmacionPago = this.dateFormat.format(Calendar.getInstance().getTime());
        this.pagoReserva.pagar(this.toursAgregados, metodoPago);
    }

}
