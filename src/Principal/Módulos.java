package Principal;

import RentaVehículos.ModuloRentaVehiculos;

import Seguros.InterfazSeguros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Módulos extends JFrame {
    private JPanel pnlMódulos;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton btnSeguros;
    private JButton btnExit;
    private JPanel pnlContenido;
    private JButton btnActualizarDatos;
    private Login login;


    public Módulos(Login login) {
        this.login = login;
        btnExit.addActionListener(e -> {
            LoginInterfaz loginInterfaz = new LoginInterfaz(login);
            loginInterfaz.crearFrame();
            dispose();
        });
        btnSeguros.addActionListener(e -> {
            InterfazSeguros interfazSeguros = new InterfazSeguros();
            setPanel(interfazSeguros.pnlOpcionesSeguro);
            crearFrame();
        });
        button3.addActionListener(e -> rentarVehiculo());
        btnActualizarDatos.addActionListener(e -> {
            ActualizarDatosInterfaz actualizarDatosInterfaz = new ActualizarDatosInterfaz(login);
            setPanel(actualizarDatosInterfaz.pnlActualizarDatos);
            crearFrame();
        });
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlMódulos);
        setVisible(true);
    }

    public void setPanel(JPanel pnlMódulos) {
        pnlContenido.add(pnlMódulos);
    }

    public void rentarVehiculo() {
        ModuloRentaVehiculos rentaVehiculos = new ModuloRentaVehiculos(this.login);
        rentaVehiculos.crearFrame();
        dispose();
    }
}
