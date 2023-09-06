package Tours;

import Principal.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    private JScrollPane scrollPanelLista;
    private JPanel pnlTourList;
    private JPanel pnlToursDisponiblesModificar;
    private JPanel pnlListaToursReservaModificar;
    private JButton btnAgregarTourModificar;
    private JButton btnEliminarTourModificar;
    private JList listToursReservaModificar;
    private JButton btnModificarReserva;
    private JSpinner jspNumPersonasModificar;
    private Tour tour; //para prueba
    private ReservaTour reservaTour;
    private ReservaTour reservaTourConfirmar = null;
    private ReservaTour reservaTourModificar = null;
    private String metodoPagoConfirmar = "";

    private ArrayList<Tour> listatemp = new ArrayList<>();

    List<Tour> tours;

    public ModuloTours(String head, Usuario usuarioVerificado){
        super (head);
        this.usuarioVerificado = usuarioVerificado;

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
                mostrarReserva(list2);
            }
        });

        btnEliminarTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTour();
                mostrarReserva(list2);
            }
        });

        btnCrearReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReserva();
                gestionReserva.agregarReserva(reservaTour);
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gestionReserva.confirmarReserva(reservaTourConfirmar, metodoPagoConfirmar);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
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
                lblFechaConfirmacion.setText(lblFechaConfirmacion.getText() + " " + reservaTourConfirmar.getFechaConfirmacionPago());

                DefaultListModel toursEnReservaModel = new DefaultListModel<>();
                ArrayList<Tour> toursEnReservaList = reservaTourConfirmar.getToursAgregados();

                for(Tour tour: toursEnReservaList) {
                    toursEnReservaModel.addElement(tour.informacionRelevante());
                }

                listTours.setModel(toursEnReservaModel);
            }
        });
        tarjetaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodoPagoConfirmar = tarjetaRadioButton.getText();
            }
        });
        transferenciaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodoPagoConfirmar = transferenciaRadioButton.getText();
            }
        });
        efectivoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodoPagoConfirmar = efectivoRadioButton.getText();
            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcion = (String)comboBox3.getSelectedItem();
                int numReserva = Integer.parseInt(opcion.split("-")[0]);
                reservaTourModificar = gestionReserva.buscarReserva(numReserva);
                DefaultListModel toursModel = new DefaultListModel<>();
                ArrayList<Tour> toursList = (ArrayList<Tour>) gestionTour.getToursDisponibles();

                for(Tour tour: toursList) {
                    toursModel.addElement(tour.informacionRelevante());
                }

                listToursModificar.setModel(toursModel);

            }
        });
        btnAgregarTourModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tours = listToursModificar.getSelectedValue().toString();
                String[] nombreTour = tours.split(",");
                String Ntour = nombreTour[0];
                listatemp.add(gestionTour.buscarTour(Ntour));
                mostrarReserva(listToursReservaModificar);
            }
        });
        btnEliminarTourModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tours = listToursReservaModificar.getSelectedValue().toString();
                String[] nombreTour = tours.split(",");
                String Ntour = nombreTour[0];
                listatemp.add(gestionTour.buscarTour(Ntour));
                mostrarReserva(listToursReservaModificar);
            }
        });
        btnModificarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservaTourModificar.setToursAgregados(listatemp);
                reservaTourModificar.setNumeroPersonas((Integer)jspNumPersonasModificar.getValue());
            }
        });
    }


    public void setReservasUsuario() {
        String nombreUsuario = this.usuarioVerificado.getNombre();
        String apellidoUsuario = this.usuarioVerificado.getApellido();
        ArrayList<ReservaTour> reservas = this.gestionReserva.getReservaciones();

        for (ReservaTour reserva: reservas) {
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
        ArrayList<Tour> tours = this.listatemp;

        for(Tour i: tours){
            model.addElement(i.informacionRelevante());
        }
        list.setModel(model);
    }

    public void agregarTour() throws ParseException {

        String tours = list1.getSelectedValue().toString();
        String[] nombreTour = tours.split(",");
        String Ntour = nombreTour[0];
        this.listatemp.add(this.gestionTour.buscarTour(Ntour));
    }

    public void eliminarTour(){
        String tours = list1.getSelectedValue().toString();
        String[] nombreTour = tours.split(",");
        String Ntour = nombreTour[0];
        this.listatemp.remove(this.gestionTour.buscarTour(Ntour));
    }

    public void crearReserva(){
        reservaTour = new ReservaTour(this.usuarioVerificado.getNombre(), this.usuarioVerificado.getApellido(), (Integer) this.spinner1.getValue() ,this.gestionTour, this.pagoReserva);
    }

}
