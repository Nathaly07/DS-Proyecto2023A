package Hospedaje.InterfacesDeUsuario;

import Hospedaje.ModuloHospedaje;
import Principal.Login;
import Principal.Módulos;

import javax.swing.*;

public class MenuHospedaje extends JFrame {

    private ModuloHospedaje moduloHospedaje;
    private Login login;
    public JPanel mainPanel;
    private JButton btnReservarHabitaciones;
    private JButton btnModificarReservas;
    private JButton btnSalir;

    public MenuHospedaje(Login login){
        this.moduloHospedaje = new ModuloHospedaje();
        this.login = login;
        btnReservarHabitaciones.addActionListener(e -> reservar());

        btnModificarReservas.addActionListener(e -> modificarReservas());

        btnSalir.addActionListener(e -> {
            Módulos mod = new Módulos(login);
            mod.crearFrame();
            dispose();
        });
    }
    public void crearFrame(){
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Modulo hospedaje");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void reservar(){
        CrearReserva crearReserva = new CrearReserva(this.login, this.moduloHospedaje.getGestionReservas());
        crearReserva.crearFrame();
    }

    public void modificarReservas(){
        VerReservas verReservas = new VerReservas(this.login, this.moduloHospedaje.getGestionReservas());
        verReservas.crearFrame();
    }

}