package Tours;

import Principal.Login;
import Principal.Usuario;
import Reservas.ReservaTour;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class ModuloTours extends JFrame{

    private GestorTour gestionTour = new GestorTour();
    private GestorReserva gestionReserva = new GestorReserva();
    private PagoReserva pagoReserva = new PagoReserva();
    private Usuario usuarioVerificado;
    public JPanel pnlOpcionesTours;
    private JButton mostrarToursDisponiblesButton;
    private JPanel panelTours;
    private JButton verReservaButton;
    private JPanel panelReserva;
    private JButton confirmarReservaButton;
    private JButton cancelarReservaButton;
    private JTabbedPane tabbedPane1;
    private JPanel pnlBuscarReserva;
    private JList listTours;
    private JLabel lblNPersonas;
    private JLabel lblFechaCreacion;
    private JLabel lblFechaConfirmacion;
    private JLabel lblTours;
    private JSpinner spinner1;
    private JLabel lblPersonas;
    private JList list1;
    private JList list2;
    private JLabel lbl1;
    private JLabel lbl2;
    private JButton btnAgregarTour;
    private JButton btnEliminarTour;
    private JButton btnCrearReserva;
    private JLabel lbl3;
    private JPanel pnlCrearReserva;
    private JPanel pnlModificarReserva;
    private JButton btnConfirmar;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JRadioButton tarjetaRadioButton;
    private JRadioButton transferenciaRadioButton;
    private JRadioButton efectivoRadioButton;
    private JPanel pnlLista;
    private JPanel pnlLista2;
    private JList listToursModificar;
    private Tour tour; //para prueba
    private ReservaTour reservaTour;
    private ReservaTour reservaTourConfirmar;

    List<Tour> tours;

    public ModuloTours(String head, Usuario usuarioVerificado){
        super (head);
        this.usuarioVerificado = usuarioVerificado;

//        panelTours.setVisible(false);
//        panelReserva.setVisible(false);
//        mostrarToursDisponiblesButton.addActionListener(e -> {
//            panelTours.setVisible(true);
//            this.buscarToursDisponibles();
//        });
//        verReservaButton.addActionListener(e -> {
//            panelReserva.setVisible(true);
//            this.verReserva();
//        });
//
//
//        confirmarReservaButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Confirmar Reserva");
//                String idReserva = JOptionPane.showInputDialog("Ingrese el id de la reserva");
//                String metodoPago = JOptionPane.showInputDialog("Seleccione el metodo de pago:\n-Paypal\n-Transferncia\n-Tarjeta de credito");
//                ReservaTour reserva = gestionReserva.getReserva(idReserva);
//
//                SimpleDateFormat format = new SimpleDateFormat("dd/M/yy");
//                LocalDate fechaActual = LocalDate.now();
//                if (reserva == null){
//                    JOptionPane.showMessageDialog(null, "Reserva no encontrada");
//                } else {
//                    reserva.setFechaConfirmacion(fechaActual.toString());
//
//                    PagoReserva pagoTour = new PagoReserva(reserva);
//                    double valorTotal = pagoTour.calcularPrecioFinal();
//                    Pago pago = new Pago(valorTotal, metodoPago);
//                    pago.pagar();
//                }
//            }
//        });
//
//        cancelarReservaButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Cancelar Reserva");
//                String idReserva = JOptionPane.showInputDialog("Ingrese el id de la reserva");
//                ReservaTour reserva = gestionReserva.getReserva(idReserva);
//
//                if (reserva == null){
//                    JOptionPane.showMessageDialog(null, "Reserva no encontrada");
//                } else {
//                    PagoReserva pagoTour = new PagoReserva(reserva);
//                    double valorDevolucion = pagoTour.calcularDevolucion();
//                    gestionReserva.cancelar(idReserva);
//                    JOptionPane.showMessageDialog(null,
//                            "La reserva se ha eliminado correctamente. /n" +
//                                    "El valor a devolver es: " + valorDevolucion,
//                            "Reserva Tour",
//                            JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
        //Listener para mostrar los tours
        pnlCrearReserva.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                mostrarToursDisponibles(list1);
            }
        });

        this.setReservasUsuario();

        btnAgregarTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    agregarTour();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                mostrarReserva(list1);
            }
        });

        btnEliminarTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTour();
                mostrarReserva(list1);
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcion = (String)comboBox2.getSelectedItem();
                int numReserva = Integer.parseInt(opcion.split("-")[0]);
                reservaTourConfirmar = gestionReserva.buscarReserva(numReserva);
                lblNPersonas.setText(lblNPersonas.getText() + " " + reservaTourConfirmar.getNumeroPersonas());
                lblFechaCreacion.setText(lblFechaCreacion.getText() + " " + reservaTourConfirmar.getFechaCreacion());
                lblFechaConfirmacion.setText(lblFechaConfirmacion.getText() + " " reservaTourConfirmar.getFechaConfirmacionPago())
            }
        });
    }

    public void setReservasUsuario() {
        String nombreUsuario = this.usuarioVerificado.getNombre();
        String apellidoUsuario = this.usuarioVerificado.getApellido();

        for (ReservaTour reserva: this.gestionReserva.getReservaciones) {
            if ((reserva.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) && (reserva.getApellidoUsuario().equalsIgnoreCase(apellidoUsuario))) {
                this.comboBox2.addItem(reserva.getNumReserva() + "-" + reserva.getNombreUsuario() + " " + reserva.getApellidoUsuario());
                this.comboBox3.addItem(reserva.getNumReserva() + "-" + reserva.getNombreUsuario() + " " + reserva.getApellidoUsuario());
            }
        }

        pnlModificarReserva.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                mostrarToursDisponibles(listToursModificar);
            }
        });
    }

    //Metodo para mostrar tours disponibles
    private void mostrarToursDisponibles(JList list){
        DefaultListModel<String> model = new DefaultListModel<>();
        List<Tour> toursDispo = this.gestionTour.getToursDisponibles();

        for (Tour i : toursDispo){
            model.addElement(i.informacionRelevante());
        }

        list.setModel(model);
    }

    public void mostrarReserva(JList list){
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<Tour> tours = this.reservaTour.getToursAgregados();

        for(Tour i: tours){
            model.addElement(i.informacionRelevante());
        }
        list.setModel(model);
    }

    public void agregarTour() throws ParseException {
        this.reservaTour.agregarTour((Tour)list1.getSelectedValue());
    }

    public void eliminarTour(){
        this.reservaTour.removerTourAgregado((Tour)list1.getSelectedValue());
    }

   /* public void buscarToursDisponibles(){
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
            JTextField limiteUsuariosTF = new JTextField(aux.getLimiteUsuarios());

            JButton botonReservar = new JButton("Reservar");

            botonReservar.addActionListener(e -> {
                ArrayList<Tour> toursAgregados = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate fechaActual = LocalDate.now();
                this.reservaTour = new ReservaTour("01","01", gestionTour, fechaActual.toString(), 100, true, toursAgregados);
                reservaTour.agregarTour(aux);
                gestionReserva.agregarReserva(reservaTour);
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
        this.tours = this.reservaTour.getToursAgregados();
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
            JTextField limiteUsuariosTF = new JTextField(aux.getLimiteUsuarios());


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


            yPos += 140;
        }
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Aceptado Existosamente");
        });
        panelReserva.add(botonAceptar);
    }*/

}
