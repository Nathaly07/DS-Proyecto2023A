import Tours.ModuloTours;

import javax.swing.*;

public class Modulos extends JFrame {
    private JPanel pnlMódulos;
    private JButton btnMódulo1;
    private JButton btnMódulo2;
    private JButton btnMódulo3;
    private JButton botonTour;
    private JButton btnExit;

    public Modulos() {
        btnExit.addActionListener(e -> {
            Login login = new Login();
            login.crearFrame();
            dispose();
        });
        btnMódulo3.addActionListener(e -> rentarVehiculo());
        botonTour.addActionListener(e -> reservarTours());
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlMódulos);
        setVisible(true);
    }
    public void rentarVehiculo(){
        ModuloRentaVehiculos rentaVehiculos = new ModuloRentaVehiculos();
        rentaVehiculos.crearFrame();
        dispose();
    }

    public void reservarTours(){
        ModuloTours reservaTours = new ModuloTours();
        reservaTours.crearFrame();
        dispose();
    }

}
