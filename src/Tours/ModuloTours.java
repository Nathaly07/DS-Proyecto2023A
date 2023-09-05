package Tours;

import Reservas.ReservaTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class ModuloTours extends JFrame{

    private GestorTour gestionTour = new GestorTour();
    private GestionReserva gestionReserva = new GestionReserva();
    public JPanel pnlOpcionesTours;
    private JButton mostrarToursDisponiblesButton;
    private JPanel panelTours;
    private JButton verReservaButton;
    private JPanel panelReserva;
    private JButton confirmarReservaButton;
    private JButton cancelarReservaButton;
    private JTabbedPane tabbedPane1;
    private JPanel pnlBuscarReserva;
    private JButton btnBuscar;
    private JTextField campoID;
    private JList listTours;
    private JLabel lblReservaId;
    private JLabel lblNPersonas;
    private JLabel lblSeguro;
    private JLabel lblFechaCreacion;
    private JLabel lblFechaConfirmacion;
    private JLabel lblTours;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JLabel lblPersonas;
    private JLabel lblSeguroCrear;
    private JList list1;
    private JList list2;
    private JLabel lbl1;
    private JLabel lbl2;
    private JButton btnAgregarTour;
    private JButton btnEliminarTour;
    private JButton btnCrearReserva;
    private JTextField textField1;
    private JButton btnBuscarParaModificar;
    private JLabel lbl3;
    private JPanel pnlCrearReserva;
    private JPanel pnlModificarReserva;
    private JButton btnConfirmar;
    private Tour tour; //para prueba
    private ReservaTour reservaTour;

    List<Tour> tours;

    public ModuloTours(String head){
        super (head);
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
    }

/*    public void crearFrame() {

        setSize(670, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlOpcionesTours);
        setVisible(true);

    }*/

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
            JTextField limiteUsuariosTF = new JTextField(aux.getLimite_usuarios());


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
    }

}
