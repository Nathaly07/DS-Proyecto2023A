import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloTours extends JFrame{

    private JPanel panel1;
    private JButton mostrarToursDisponiblesButton;
    private JPanel panelTours;
    private JButton verReservaButton;
    private JPanel panelReserva;

    public ModuloTours(){
        panelTours.setVisible(false);
        panelReserva.setVisible(false);
        mostrarToursDisponiblesButton.addActionListener(e -> panelTours.setVisible(true));
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

}
