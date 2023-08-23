package Tours;

import Pagos.Pago;
import Reservas.ReservaTour;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TestTour {

    private Gestion_Reserva gestorReserva;

    public TestTour(Gestion_Reserva gestorReserva) {
        this.gestorReserva = gestorReserva;
    }

    public void initTour() {
        boolean flag = true;

        while (flag == true) {
            int op = Integer.parseInt(
                    JOptionPane.showInputDialog(null,
                            "1. Buscar Tours \n2. Reservar Tour\n3. Modificar Tour\n4. Eliminar Tour\n5. Confirmar Tour\n6. Salir\n ",
                            "Menu",
                            JOptionPane.QUESTION_MESSAGE));

            switch (op) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Buscar Tours");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Reservar Tour");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Modificar Tour");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Eliminar Tour");
                    String nombreTour = JOptionPane.showInputDialog(null,
                        "Ingrese el nombre del Tour a eliminar", 
                        "Eliminar Tour", JOptionPane.QUESTION_MESSAGE);
                    //gestorReserva.eliminarTour(nombreTour);
                    JOptionPane.showMessageDialog(null, 
                        "Tour eliminado", "Eliminar Tour", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Confirmar Reserva");
                    String idReserva = JOptionPane.showInputDialog("Ingrese el id de la reserva");
                    String metodoPago = JOptionPane.showInputDialog("Seleccione el metodo de pago:\n-Paypal\n-Transferncia\n-Tarjeta de credito");
                    this.confirmarReserva(idReserva, metodoPago);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Salir");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        }
    }
    public void confirmarReserva(String idReserva, String metodoPago){
        ReservaTour reserva = this.gestorReserva.getReserva(idReserva);

        LocalDate fechaActual = LocalDate.now();

        reserva.setFechaConfirmacion(fechaActual.toString());

        PagoTour pagoTour = new PagoTour(reserva);
        double valorTotal = pagoTour.calcularPrecioFinal();
        Pago pago = new Pago(valorTotal, metodoPago);
        pago.pagar();
    }
}
