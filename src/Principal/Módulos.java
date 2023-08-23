package Principal;

import Hospedaje.InterfazHospedaje;
import RentaVehículos.ModuloRentaVehiculos;

import Seguros.InterfazSeguros;

import javax.swing.*;

public class Módulos extends JFrame {
    private JPanel pnlMódulos;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton btnHospedaje;
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
        btnHospedaje.addActionListener(e -> {
            InterfazHospedaje interfazHospedaje = new InterfazHospedaje();
            setPanel(interfazHospedaje.mainPanel);
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
