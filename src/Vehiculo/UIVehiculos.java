package Vehiculo;

import Principal.Sesion;
import Tours.GestorReserva;
import Vuelos.Logica.GestorReservasAsiento;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private JButton btnUsaFechaModificación;
    private JButton actualizarCatalogoButton;
    private JTable table1;
    private JDateChooser dateChooserInicio = new JDateChooser();
    private JDateChooser dateChooserFinal = new JDateChooser();

    //Clases diagrama
   // private  CatalogoVehiculos catalogoVehiculos;
    private static GestorReservaVehiculo gestorRentas;


    public UIVehiculos(Sesion sesion, GestorReservasAsiento gra, GestorReserva gestionReserva) {

        gestorRentas = new GestorReservaVehiculo(gra);

        btnCatalogo.addActionListener(e -> { // METODO DESPLEGAR CATALOGO
            int fechaValida = dateChooserFinal.getDate().compareTo(dateChooserInicio.getDate());
            if (txtOrigen.getText().isEmpty() || txtRetorno.getText().isEmpty() || fechaValida<0) {
                JOptionPane.showMessageDialog(null,"Datos no validos");

            }else{
                gestorRentas.getCatalogo().mostrarVehiculos(pnlCatalogo1);
                GestorReservaVehiculo.getRenta().recolectarDatosReserva(txtOrigen.getText(),txtRetorno.getText(),dateChooserFinal.getDate(),dateChooserInicio.getDate());
            }


        });
        btnCarrito.addActionListener(e -> { // METODO VER RENTAS
            table1 = new JTable();
            gestorRentas.verInfoCarrito(table1,pnlCatalogo1);
        });


        //calendario
        pnlCalendario1.add(dateChooserInicio);
        pnlCalendario2.add(dateChooserFinal);

        btnUsaFechaModificación.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<String> fechasReservas = gestionReserva.getRangosFechas(sesion.getUsuarioVerificado().getNombreUsuario(),
                            sesion.getUsuarioVerificado().getApellido());
                    asignarFechasEnUI(fechasReservas);

                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            private void asignarFechasEnUI(ArrayList<String> fechasReservas) throws ParseException {
                if (fechasReservas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No existe un tours reservados");
                } else {
                    System.out.println(fechasReservas);
                    String[] fechas = fechasReservas.get(0).split("-"); //TODO: maybe toca cambiar el número
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dateChooserInicio.setDate(format.parse(fechas[0]));
                    dateChooserFinal.setDate(format.parse(fechas[1]));
                    dateChooserInicio.updateUI();
                    dateChooserFinal.updateUI();
                }
            }
        });
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

