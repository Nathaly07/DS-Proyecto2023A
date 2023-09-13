package Principal;

import Hospedaje.InterfacesDeUsuario.MenuHospedaje;
import Tours.GestorReserva;
import Vehiculo.UIVehiculos;

import Seguros.InterfacesGráficas.InterfazSeguro;
import Tours.ModuloTours;
import Vuelos.GUI.ModuloVuelos;
import Vuelos.Logica.GestorReservasAsiento;

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
    private Sesion sesion;

    private GestorReserva gestionReserva = new GestorReserva();
    private GestorReservasAsiento gra = new GestorReservasAsiento();

    public Módulos(Sesion sesion) {
        this.sesion = sesion;

        btnVuelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModuloVuelos moduloVuelos= new ModuloVuelos(sesion,gra);
                setPanel(moduloVuelos.plnPrincipalVuelos);
                crearFrame();
            }
        });

        btnExit.addActionListener(e -> {
            LoginInterfaz loginInterfaz = new LoginInterfaz(sesion);
            loginInterfaz.crearFrame();
            dispose();
        });
        btnSeguros.addActionListener(e -> {
            InterfazSeguro interfazSeguro = new InterfazSeguro(this.sesion.getUsuarioVerificado());
            setPanel(interfazSeguro.panelPrincipal);
            crearFrame();
        });
        button3.addActionListener(e ->{
            UIVehiculos rentaVehiculo = new UIVehiculos(sesion,gra,gestionReserva);
            setPanel(rentaVehiculo.pnlPrincipal);
            crearFrame();
        });



        btnHospedaje.addActionListener(e -> hospedaje());
        btnActualizarDatos.addActionListener(e -> {
            ActualizarDatosInterfaz actualizarDatosInterfaz = new ActualizarDatosInterfaz(sesion);
            setPanel(actualizarDatosInterfaz.pnlActualizarDatos);
            crearFrame();
        });
        toursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModuloTours moduloTours = new ModuloTours("Reservas - Tour", sesion.getUsuarioVerificado(), gestionReserva);
                setPanel(moduloTours.pnlOpcionesTours);
                crearFrame();
            }
        });
    }


    public void crearFrame() {
        setTitle("Agencia de Viajes");
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

    public void rentarVehiculo() {
        UIVehiculos rentaVehiculos = new UIVehiculos(this.sesion, gra, gestionReserva);
        rentaVehiculos.crearFrame();
        dispose();
    }
    public void hospedaje(){
        MenuHospedaje menuHospedaje = new MenuHospedaje(this.sesion);
        setPanel(menuHospedaje.mainPanel);
        crearFrame();
    }

}
