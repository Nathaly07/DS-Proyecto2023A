package Vuelos;

import Vuelos.Logica.PagoReservaAsiento;
import Vuelos.Logica.ReservaAsiento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagoVuelos extends JFrame {


    public JPanel JPPagoVuelos;
    private JButton cerrarButton;
    private JButton pagarButton;
    private JTextField numVip;
    private JTextField numTu;
    private JTextField valorVip;
    private JTextField valortur;
    private JTextField subtotal;
    private JTextField iva;
    private JTextField total;
    private JRadioButton jrbTarjeta;
    private JRadioButton jrbTransferencia;
    private JRadioButton jrbEfectivo;
    private ButtonGroup MétodoDePago;
    private PagoReservaAsiento pago;
    private ModuloVuelos moduloVuelos;
    public PagoVuelos(ModuloVuelos moduloVuelos) {
        this.moduloVuelos = moduloVuelos;
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduloVuelos.cerrarDialog();
            }
        });
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MétodoDePago.add(jrbEfectivo);
                MétodoDePago.add(jrbTransferencia);
                MétodoDePago.add(jrbTarjeta);
                if (MétodoDePago.getSelection() == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un método de pago", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    pago.Pagar();
                    moduloVuelos.cerrarDialog();
                }

            }
        });
    }

    public void crearframe() {
        setTitle("Confirmar Reserva");
        setContentPane(JPPagoVuelos);
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void InicioDatos(ReservaAsiento reserva){
        numVip.setText(reserva.cantidadAsientosReservadosPremium() +"");
        numTu.setText(reserva.cantidadAsientosReservadosTurista() +"");
        valorVip.setText(reserva.generarCostoTotalPremium() +"");
        valortur.setText(reserva.generarCostoTotalTurista() + "" );
        subtotal.setText(reserva.generarCostoTotal() +"");
        pago = new PagoReservaAsiento(reserva);
        iva.setText(pago.calcularIVA()+ "");
        total.setText(pago.calcularCostoTotalReservados() +"");

    }
}
