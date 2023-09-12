package Vuelos.GUI;

import Vuelos.Logica.PagoReservaAsiento;
import Vuelos.Logica.ReservaAsiento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagoVuelos extends JFrame {


    public JPanel pnlPagoVuelos;
    private JButton btnCerrar;
    private JButton btnPagar;
    private JTextField txtNumeroAsientosVip;
    private JTextField txtNumeroAsientosTurista;
    private JTextField txtValorAsientosVip;
    private JTextField txtvalorAsientoTurista;
    private JTextField txtSubtotal;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JRadioButton jrbTarjeta;
    private JRadioButton jrbTransferencia;
    private JRadioButton jrbEfectivo;
    private ButtonGroup btgMetodoDePago;
    private PagoReservaAsiento pago;
    private ModuloVuelos moduloVuelos;
    public PagoVuelos(ModuloVuelos moduloVuelos) {
        this.moduloVuelos = moduloVuelos;
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduloVuelos.cerrarDialog();
            }
        });
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                crearBtgMetodoPago();
                if (btgMetodoDePago.getSelection() == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un método de pago", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    String metodo = obtenerStringMetodoPago();
                    btgMetodoDePago.clearSelection();
                    pago.Pagar(metodo);
                    moduloVuelos.cerrarDialog();
                }

            }
        });
    }

    private String obtenerStringMetodoPago() {
        String metodo = "";
        if(jrbEfectivo.isSelected()){
           metodo = "Efectivo";
        } else if (jrbTransferencia.isSelected()) {
            metodo = "Transferencia";
        }else{
            metodo =  "Tarjeta";
        }
        return metodo;
    }

    private void crearBtgMetodoPago() {
        btgMetodoDePago.add(jrbEfectivo);
        btgMetodoDePago.add(jrbTransferencia);
        btgMetodoDePago.add(jrbTarjeta);
    }

    public void crearframe() {
        setTitle("Confirmar Reserva");
        setContentPane(pnlPagoVuelos);
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void iniciarDatos(ReservaAsiento reserva){
        txtNumeroAsientosVip.setText(reserva.obtenerCantidadAsientosReservadosPremium() +"");
        txtNumeroAsientosTurista.setText(reserva.obtenerCantidadAsientosReservadosTurista() +"");
        txtValorAsientosVip.setText(reserva.generarCostoTotalPremium() +"");
        txtvalorAsientoTurista.setText(reserva.generarCostoTotalTurista() + "" );
        txtSubtotal.setText(reserva.generarCostoTotal() +"");
        pago = new PagoReservaAsiento(reserva);
        txtIVA.setText(pago.calcularIVA()+ "");
        txtTotal.setText(pago.calcularCostoTotalReservados() +"");

    }
}
