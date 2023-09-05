package Vuelos;

import Reservas.Reserva;
import Reservas.ReservaAsiento;
import Vuelos.Logica.Asiento;
import Vuelos.Logica.CarritoAsientos;
import Vuelos.Logica.PagoReservaAsiento;
import Vuelos.Logica.Vuelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class PagoVuelos extends JFrame {


    public JPanel JPPagoVuelos;
    private JButton cerrarButton;
    private JButton pagarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;

    private PagoReservaAsiento pagoReservaAsiento;
    private double costoTotal;
    private Reserva reserva;

    private Vuelo vuelo;

    private Asiento asiento;

    private SelectorDeAsientos selectorDeAsientos;
    private CarritoAsientos carrito;



    public PagoVuelos() {
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.cantidadAsientosPremiumReservados();
            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.cantidadAsientosTuristaReservados();
            }
        });

        textField5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.calcularCostoTotalPremium();
            }
        });

        textField6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.calcularCostoTotalTurista();
            }
        });
        textField7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.calcularCostoTotalReservados();
            }
        });

        textField8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.calcularIVA();
            }
        });

        textField9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoReservaAsiento.calcularCostoTotal();
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


    public void setAsiento(CarritoAsientos carrito) {
        this.carrito = carrito;
    }
}
