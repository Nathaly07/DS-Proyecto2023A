package Vuelos;

import Principal.Login;
import Principal.Módulos;

import javax.swing.*;

public class ModuloVuelos extends JFrame{
    private JButton mostrarCatalogoButton;
    private JButton regresarButton;
    private JButton salirButton;
    private JLabel title;
    private JPanel plnPrincipalVuelos;
    private JTextField textField1;
    private JTextField textField2;
    private JButton buscarVuelosButton;
    private JTable table1;
    private JPanel pnlListaVuelos;


    public ModuloVuelos(Login login){
        setContentPane(plnPrincipalVuelos);
        pnlListaVuelos.setVisible(false);
        mostrarCatalogoButton.addActionListener(e -> pnlListaVuelos.setVisible(true));
        regresarButton.addActionListener(e -> {
            Módulos módulos = new Módulos(login);
            módulos.crearFrame();
            dispose();
        });

        salirButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
    }

    public void crearframe() {
        setTitle("Modulo Vuelos");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
