import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloRentaVehiculos extends JFrame {
    private JPanel pnlPrincipal;
    private JButton btnCatalogo;
    private JPanel pnlCalendario1;
    private JButton seleccionarVehiculoButton;
    private JButton seleccionarVehiculoButton1;
    private JLabel lblInfoV1;
    private JPanel lblCatalogo;
    private JPanel pnlCatalogo1;
    private JLabel lblV1;
    private JLabel lblV2;
    private JLabel lblInfoV2;
    private JButton btnRegresar;
    private JButton btnSalir;
    private JPanel pnlCalendario2;
    private JLabel lblImgV1;
    private JLabel lblImgV2;
    private JButton btnCarrito;
    private JTextField txtOrigen;
    private JTextField txtRetorno;
    private JLabel lblOrigen;
    private JLabel lblRetorno;
    private JDateChooser dateChooserInicio = new JDateChooser();
    private JDateChooser dateChooserFinal = new JDateChooser();

    public ModuloRentaVehiculos() {
        pnlCatalogo1.setVisible(false);
        btnCarrito.setVisible(false);
        btnCatalogo.addActionListener(e -> pnlCatalogo1.setVisible(true));
        btnRegresar.addActionListener(e -> {
            Modulos modulos = new Modulos();
            modulos.crearFrame();
            dispose();
        });
        btnSalir.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
        //calendario
        pnlCalendario1.add(dateChooserInicio);
        pnlCalendario2.add(dateChooserFinal);
    }

    public void crearFrame() {

            setSize(1000, 700);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
            add(pnlPrincipal);
            setVisible(true);

    }
}
