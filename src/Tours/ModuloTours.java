package Tours;

import java.util.ArrayList;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloTours extends JFrame{

    private Gestion_Tour gestionTour;
    private JPanel panel1;
    private JButton mostrarToursDisponiblesButton;
    private JPanel panelTours;
    private JButton verReservaButton;
    private JPanel panelReserva;

    public ModuloTours(){
        panelTours.setVisible(false);
        panelReserva.setVisible(false);
        mostrarToursDisponiblesButton.addActionListener(e -> {
            panelTours.setVisible(true);
            this.buscarToursDisponibles();
        });
        verReservaButton.addActionListener(e -> panelReserva.setVisible(true));


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
        List<Tour> tours = this.gestionTour.getToursDisponibles();
        for(Tour aux: tours){
            JLabel nombreLabel = new JLabel("Nombre: ");
            JLabel precioLabel = new JLabel("Precio: ");
            JLabel infoGuiaLabel = new JLabel("Guia: ");
            JLabel duracionLabel = new JLabel("Duracion: ");
            JLabel limiteUsuariosLabel = new JLabel("Limite usuarios: ");
            JTextField nombreTF = new JTextField(aux.getNombre());
            JTextField precioTF = new JTextField(String.valueOf(aux.getPrecio()));
            JTextField infoGuiaTF = new JTextField(aux.getInfo_guia());
            JTextField duracionTF = new JTextField(aux.getDuracion());
            JTextField limiteUsuariosTF = new JTextField(aux.getLimite_usuarios());

            add(nombreLabel);
            add(nombreTF);
            add(precioLabel);
            add(precioTF);
            add(infoGuiaLabel);
            add(infoGuiaTF);
            add(duracionLabel);
            add(duracionTF);
            add(limiteUsuariosLabel);
            add(limiteUsuariosTF);
        }
    }

}
