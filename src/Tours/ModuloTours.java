package Tours;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Reservas.ReservaTour;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloTours extends JFrame{

    private Gestion_Tour gestionTour = new Gestion_Tour();
    private Gestion_Reserva gestionReserva;
    private JPanel panel1;
    private JButton mostrarToursDisponiblesButton;
    private JPanel panelTours;
    private JButton verReservaButton;
    private JPanel panelReserva;
    private Tour tour; //para prueba
    private ReservaTour reservaTour;
    List<Tour> tours;

    public ModuloTours(){
        panelTours.setVisible(false);
        panelReserva.setVisible(false);
        mostrarToursDisponiblesButton.addActionListener(e -> {
            panelTours.setVisible(true);
            this.buscarToursDisponibles();
        });
        verReservaButton.addActionListener(e -> {
            panelReserva.setVisible(true);
            this.verReserva();
        });


    }

    public void crearFrame() {

        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panel1);
        setVisible(true);

    }

    public void buscarToursDisponibles(){
        int yPos = 10;

        Tour tour1 = new Tour("01", "Tour 1", 100.2, "Guia", "1 mes", 100, gestionReserva);
        Tour tour2 = new Tour("02", "Tour 2", 100.2, "Guia", "1 mes", 100, gestionReserva);
        this.gestionTour.nuevoTour(tour1);
        this.gestionTour.nuevoTour(tour2);
        this.tours = this.gestionTour.getToursDisponibles();
        for(Tour aux: this.tours){


            JLabel nombreLabel = new JLabel("Nombre: ");
            JTextField nombreTF = new JTextField(aux.getNombre());
            JLabel precioLabel = new JLabel("Precio: ");
            JTextField precioTF = new JTextField(String.valueOf(aux.getPrecio()));
            JLabel infoGuiaLabel = new JLabel("Guia: ");
            JTextField infoGuiaTF = new JTextField(aux.getInfo_guia());
            JLabel duracionLabel = new JLabel("Duracion: ");
            JTextField duracionTF = new JTextField(aux.getDuracion());
            JLabel limiteUsuariosLabel = new JLabel("Limite usuarios: ");
            JTextField limiteUsuariosTF = new JTextField(aux.getLimite_usuarios());

            JButton botonReservar = new JButton("Reservar");

            botonReservar.addActionListener(e -> {
                ArrayList<Tour> toursAgregados = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                this.reservaTour = new ReservaTour("01","01", gestionTour, "19/01/2023", "12/02/2023", 100, true, toursAgregados);
                reservaTour.agregarTour(aux);
            });

            nombreLabel.setBounds(10, yPos, 100, 25);
            nombreTF.setBounds(110, yPos, 150, 25);
            precioLabel.setBounds(10, yPos + 30, 100, 25);
            precioTF.setBounds(110, yPos + 30, 60, 25);
            infoGuiaLabel.setBounds(10, yPos + 60, 100, 25);
            infoGuiaTF.setBounds(110, yPos + 60, 150, 25);
            duracionLabel.setBounds(10, yPos + 90, 100, 25);
            duracionTF.setBounds(110, yPos + 90, 150, 25);
            limiteUsuariosLabel.setBounds(10, yPos + 120, 100, 25);
            limiteUsuariosTF.setBounds(110, yPos + 120, 60, 25);
            botonReservar.setBounds(10, yPos + 150, 100, 25);

            panelTours.add(nombreLabel);
            panelTours.add(nombreTF);
            panelTours.add(precioLabel);
            panelTours.add(precioTF);
            panelTours.add(infoGuiaLabel);
            panelTours.add(infoGuiaTF);
            panelTours.add(duracionLabel);
            panelTours.add(duracionTF);
            panelTours.add(limiteUsuariosLabel);
            panelTours.add(limiteUsuariosTF);
            panelTours.add(botonReservar);

            yPos += 140;
        }


    }
    public void verReserva(){
        this.reservaTour.getToursAgregados();
        panelReserva.setVisible(true);
        int yPos = 10;
        for(Tour aux: this.reservaTour.getToursAgregados()){
            JLabel nombreLabel = new JLabel("Nombre: ");
            JTextField nombreTF = new JTextField(aux.getNombre());
            JLabel precioLabel = new JLabel("Precio: ");
            JTextField precioTF = new JTextField(String.valueOf(aux.getPrecio()));
            JLabel infoGuiaLabel = new JLabel("Guia: ");
            JTextField infoGuiaTF = new JTextField(aux.getInfo_guia());
            JLabel duracionLabel = new JLabel("Duracion: ");
            JTextField duracionTF = new JTextField(aux.getDuracion());
            JLabel limiteUsuariosLabel = new JLabel("Limite usuarios: ");
            JTextField limiteUsuariosTF = new JTextField(aux.getLimite_usuarios());

            JButton botonEliminarTour = new JButton("Eliminar");
            JButton botonMoficiarTour = new JButton("Modificar");


            botonEliminarTour.addActionListener(e -> {
                this.reservaTour.eliminarTour(aux);
                nombreLabel.setText(" ");
                nombreTF.setText("");
            });





            nombreLabel.setBounds(10, yPos, 100, 25);
            nombreTF.setBounds(110, yPos, 150, 25);
            precioLabel.setBounds(10, yPos + 30, 100, 25);
            precioTF.setBounds(110, yPos + 30, 60, 25);
            infoGuiaLabel.setBounds(10, yPos + 60, 100, 25);
            infoGuiaTF.setBounds(110, yPos + 60, 150, 25);
            duracionLabel.setBounds(10, yPos + 90, 100, 25);
            duracionTF.setBounds(110, yPos + 90, 150, 25);
            limiteUsuariosLabel.setBounds(10, yPos + 120, 100, 25);
            limiteUsuariosTF.setBounds(110, yPos + 120, 60, 25);
            botonMoficiarTour.setBounds(10, yPos + 150, 60, 25);
            botonEliminarTour.setBounds(10, yPos + 180, 60, 25);


            panelReserva.add(nombreLabel);
            panelReserva.add(nombreTF);
            panelReserva.add(precioLabel);
            panelReserva.add(precioTF);
            panelReserva.add(infoGuiaLabel);
            panelReserva.add(infoGuiaTF);
            panelReserva.add(duracionLabel);
            panelReserva.add(duracionTF);
            panelReserva.add(limiteUsuariosLabel);
            panelReserva.add(limiteUsuariosTF);
            panelReserva.add(botonMoficiarTour);
            panelReserva.add(botonEliminarTour);



            yPos += 140;
        }
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Aceptado Existosamente");
        });
        panelReserva.add(botonAceptar);
    }

}
