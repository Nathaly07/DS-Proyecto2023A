package Vehiculo;

import Principal.Sesion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class UIVehiculos extends JPanel {
    public JPanel pnlPrincipal;
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
    private JScrollPane scroll;
    private JButton actualizarCatalogoButton;
    private JTable table1;
    private JDateChooser dateChooserInicio = new JDateChooser();
    private JDateChooser dateChooserFinal = new JDateChooser();

    //Clases diagrama
   // private  CatalogoVehiculos catalogoVehiculos;
    private static GestoReservaVehiculo gestorRentas;


    public UIVehiculos(Sesion sesion) {

        gestorRentas = new GestoReservaVehiculo();


        btnCatalogo.addActionListener(e -> { // METODO DESPLEGAR CATALOGO
            int fechaValida = dateChooserFinal.getDate().compareTo(dateChooserInicio.getDate());
            if (txtOrigen.getText().isEmpty() || txtRetorno.getText().isEmpty() || fechaValida<0) {
                JOptionPane.showMessageDialog(null,"Datos no validos");

            }else{
                gestorRentas.getCatalogo().mostrarVehiculos(pnlCatalogo1);
                GestoReservaVehiculo.getRenta().recolectarDatosReserva(txtOrigen.getText(),txtRetorno.getText(),dateChooserFinal.getDate(),dateChooserInicio.getDate());
            }


        });
        btnCarrito.addActionListener(e -> { // METODO VER RENTAS
            table1 = new JTable();
            gestorRentas.verInfoCarrito(table1,pnlCatalogo1);
        });


        //calendario
        pnlCalendario1.add(dateChooserInicio);
        pnlCalendario2.add(dateChooserFinal);

    }

    public void crearFrame() {
            setSize(1000, 600);
            //setResizable(false);
            //Agregando scroll
            final JScrollPane scroller = new JScrollPane(pnlPrincipal,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroller.setViewportView(pnlPrincipal);
            //add(pnlPrincipal);
            this.add(scroller);
            setVisible(true);

    }




}

