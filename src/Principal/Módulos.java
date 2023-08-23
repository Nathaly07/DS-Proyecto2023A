package Principal;

import ModuloRentaVehiculos.ModuloRentaVehiculos;
import Hospedaje.InterfacesDeUsuario.MenuHospedaje;

import Seguros.InterfazSeguros;
import Tours.ModuloTours;
import Vuelos.ModuloVuelos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Módulos extends JFrame {
    private JPanel pnlMódulos;
    private JButton btnVuelos;
    private JButton toursButton;
    private JButton button3;
    private JButton btnHospedaje;
    private JButton btnSeguros;
    private JButton btnExit;
    private JPanel pnlContenido;
    private JButton btnActualizarDatos;
    private Login login;


    public Módulos(Login login) {
        this.login = login;

        btnVuelos.addActionListener(e -> reservarVuelo());
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
        btnHospedaje.addActionListener(e -> hospedaje());
        btnActualizarDatos.addActionListener(e -> {
            ActualizarDatosInterfaz actualizarDatosInterfaz = new ActualizarDatosInterfaz(login);
            setPanel(actualizarDatosInterfaz.pnlActualizarDatos);
            crearFrame();
        });
        toursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModuloTours moduloTours = new ModuloTours();
                moduloTours.crearFrame();
            }
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
        pnlContenido.removeAll();
        pnlContenido.add(pnlMódulos);
    }

    private void reservarVuelo() {
        ModuloVuelos moduloVuelos = new ModuloVuelos(login);
        moduloVuelos.crearframe();
        dispose();
    }
    public void rentarVehiculo() {
        ModuloRentaVehiculos rentaVehiculos = new ModuloRentaVehiculos(this.login);
        rentaVehiculos.crearFrame();
        dispose();
    }
    public void hospedaje(){
        MenuHospedaje menuHospedaje = new MenuHospedaje(this.login);
        menuHospedaje.crearFrame();
        dispose();
    }

}
