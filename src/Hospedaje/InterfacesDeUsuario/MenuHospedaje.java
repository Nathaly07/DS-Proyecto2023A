package Hospedaje.InterfacesDeUsuario;

import Hospedaje.Habitaciones.GestionHabitaciones;
import Hospedaje.Reservas.GestionReservas;
import Principal.Sesion;
import Principal.Módulos;

import javax.swing.*;

public class MenuHospedaje extends JFrame {
    private GestionHabitaciones gestionHabitaciones;
    private GestionReservas gestionReservas;
    private Sesion sesion;
    public JPanel mainPanel;
    private JButton btnReservarHabitaciones;
    private JButton btnModificarReservas;
    private JButton btnSalir;

    public MenuHospedaje(Sesion sesion){
        this.gestionHabitaciones = new GestionHabitaciones();
        this.gestionReservas = new GestionReservas(this.gestionHabitaciones);
        this.sesion = sesion;
        btnReservarHabitaciones.addActionListener(e -> reservar());

        btnModificarReservas.addActionListener(e -> modificarReservas());

        btnSalir.addActionListener(e -> {
            Módulos mod = new Módulos(sesion);
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
        CrearReserva crearReserva = new CrearReserva(this.sesion, this.gestionReservas);
        crearReserva.crearFrame();
    }

    public void modificarReservas(){
        VerReservas verReservas = new VerReservas(this.sesion, this.gestionReservas);
        verReservas.crearFrame();
    }

}