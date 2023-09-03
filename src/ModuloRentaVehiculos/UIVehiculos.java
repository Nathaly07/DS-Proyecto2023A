package ModuloRentaVehiculos;

import Principal.Login;
import Principal.Módulos;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class UIVehiculos extends JFrame {
    private JPanel pnlPrincipal;
    private JButton btnCatalogo;
    private JPanel pnlCalendario1;
    private JPanel pnlCatalogo1;
    private JButton btnRegresar;
    private JButton btnSalir;
    private JPanel pnlCalendario2;
    private JButton btnCarrito;
    private JTextField txtOrigen;
    private JTextField txtRetorno;
    private JLabel lblOrigen;
    private JLabel lblRetorno;
    private JButton actualizarCatalogoButton;
    private JTable table1;
    private JDateChooser dateChooserInicio = new JDateChooser();
    private JDateChooser dateChooserFinal = new JDateChooser();

    //Clases diagrama
    private  CatalogoVehiculos catalogoVehiculos;
    private static CarritoRentas carritoRentas;


    public UIVehiculos(Login login) {
        catalogoVehiculos = new CatalogoVehiculos();
        carritoRentas = new CarritoRentas();


        btnCatalogo.addActionListener(e -> { // METODO DESPLEGAR CATALOGO

            if (txtOrigen.getText().isEmpty() || txtRetorno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Por favor ingrese todos los datos");

            }else{
                catalogoVehiculos.mostrarVehiculos(pnlCatalogo1);
                CarritoRentas.getRenta().recolectarDatosRenta(lblOrigen.getText(),lblRetorno.getText(),dateChooserFinal.getDate(),dateChooserInicio.getDate());
            }


        });
        btnCarrito.addActionListener(e -> { // METODO VER RENTAS
            table1 = new JTable();
            carritoRentas.verInfoCarrito(table1,pnlCatalogo1);
        });

        btnRegresar.addActionListener(e -> {
            Módulos módulos = new Módulos(login);
            módulos.crearFrame();
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
            setTitle("Modulo Renta Vehiculos");
            setSize(1000, 600);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //setResizable(false);
            //Agregando scroll
            final JScrollPane scroller = new JScrollPane(pnlPrincipal,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroller.setViewportView(pnlPrincipal);

            setLocationRelativeTo(null);
            //add(pnlPrincipal);
            this.add(scroller);
            this.pack();
            setVisible(true);

    }




}

