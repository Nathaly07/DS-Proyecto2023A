package Tours;

import Principal.Sesion;
import Principal.Usuario;

import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.HashSet;

public class ModuloTours extends JFrame{

    private GestorTour gestionTour = new GestorTour();

    private GestorReserva gestionReserva;
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
    private JPanel pnlEliminarReserva;
    private JTextArea txtAreaDescripcionTour;
    private JScrollPane pnlAreaDescripcionTour;
    private JTextArea txtModificarDetallesTour;
    private JComboBox boxCancelar;
    private JLabel lblNumCancelar;
    private JLabel lblFechaCreacionCancelar;
    private JLabel lblFechaConfirmacionCancelar;
    private JButton btnEliminarReserva;
    private JList listaEliminar;
    private Tour tour; //para prueba
    private ReservaTour reservaTour;
    private ReservaTour reservaTourConfirmar = null;
    private ReservaTour reservaTourModificar = null;
    private ReservaTour reservaTourCancelar= null;
    private String metodoPagoConfirmar = "";
    private ArrayList<Tour> listaToursAgregadosReserva = new ArrayList<>();

    List<Tour> tours;

    HashSet<String> reservasUnicas = new HashSet<>();

    //Instancia para acceder a la información de la sesión
    Sesion sesion = Sesion.getInstance();

    public ModuloTours(String head, Usuario usuarioVerificado, GestorReserva gestionReserva){
        super (head);
        this.usuarioVerificado = usuarioVerificado;
        this.gestionReserva = gestionReserva;

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
                String tour = list1.getSelectedValue().toString();
                if(verificarTourYaAgregado(tour, list2)) {
                    String nombreTour = tour.split("->")[0];
                    listaToursAgregadosReserva.add(gestionTour.buscarTour(nombreTour));
                    mostrarToursEnReserva(list2);
                }
            }
        });

        btnEliminarTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTour = list2.getSelectedValue().toString().split("->")[0];
                listaToursAgregadosReserva.remove(gestionTour.buscarTour(nombreTour));
                mostrarToursEnReserva(list2);
            }
        });

        btnCrearReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservaTour = new ReservaTour(usuarioVerificado.getNombre(), usuarioVerificado.getApellido(), (Integer) spinner1.getValue() ,gestionTour, pagoReserva);
                gestionReserva.agregarReserva(reservaTour);
                reservaTour.setToursAgregados(listaToursAgregadosReserva);
                listaToursAgregadosReserva = new ArrayList<Tour>();
                setReservasUsuario();
                list2.setModel(new DefaultListModel<>());
                spinner1.setValue(0);
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
                lblNPersonas.setText("# de personas:");
                lblFechaCreacion.setText("Fecha de Creacion de Reserva:");
                lblFechaConfirmacion.setText("Fecha de Confirmacion de Reserva:");
                lblNPersonas.setText(lblNPersonas.getText() + " " + reservaTourConfirmar.getNumeroPersonas());
                lblFechaCreacion.setText(lblFechaCreacion.getText() + " " + reservaTourConfirmar.getFechaCreacion());
                lblFechaConfirmacion.setText(lblFechaConfirmacion.getText() + " " + reservaTourConfirmar.getFechaConfirmacionPago());

                DefaultListModel toursEnReservaModel = new DefaultListModel<>();
                ArrayList<Tour> toursEnReservaList = reservaTourConfirmar.getToursAgregados();

                for(Tour tour: toursEnReservaList) {
                    toursEnReservaModel.addElement(tour.getInformacionRelevante());
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
                listaToursAgregadosReserva = new ArrayList<Tour>();
                String opcion = (String)comboBox3.getSelectedItem();
                int numReserva = Integer.parseInt(opcion.split("-")[0]);
                reservaTourModificar = gestionReserva.buscarReserva(numReserva);
                DefaultListModel toursModel = new DefaultListModel<>();
                ArrayList<Tour> toursList = (ArrayList<Tour>) gestionTour.getToursDisponibles(sesion.getDestinoComun(), sesion.getFechaComun());

                for(Tour tour: toursList) {
                    toursModel.addElement(tour.getNombre() + "-> Inicia: " + tour.getFechaInicio());
                }
                listToursModificar.setModel(toursModel);

                toursModel = new DefaultListModel<>();
                toursList = reservaTourModificar.getToursAgregados();

                for(Tour tour: toursList) {
                    toursModel.addElement(tour.getNombre() + "-> Inicia: " + tour.getFechaInicio());
                    listaToursAgregadosReserva.add(tour);
                }
                listToursReservaModificar.setModel(toursModel);

            }
        });
        btnAgregarTourModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tour = listToursModificar.getSelectedValue().toString();
                if(verificarTourYaAgregado(tour, listToursReservaModificar)){
                    String nombreTour = tour.split("->")[0];
                    listaToursAgregadosReserva.add(gestionTour.buscarTour(nombreTour));
                    mostrarToursEnReserva(listToursReservaModificar);
                }
            }
        });
        btnEliminarTourModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTour = listToursReservaModificar.getSelectedValue().toString().split("->")[0];;
                listaToursAgregadosReserva.remove(gestionTour.buscarTour(nombreTour));
                mostrarToursEnReserva(listToursReservaModificar);
            }
        });
        btnModificarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservaTourModificar.setToursAgregados(listaToursAgregadosReserva);
                reservaTourModificar.setNumeroPersonas((Integer)jspNumPersonasModificar.getValue());
                JOptionPane.showMessageDialog(null,
                        "Se ha modificado su reserva exitosamente",
                        "Gestor Reserva",
                        JOptionPane.INFORMATION_MESSAGE);
                listaToursAgregadosReserva = new ArrayList<Tour>();
                setReservasUsuario();
                listToursReservaModificar.setModel(new DefaultListModel());
                jspNumPersonasModificar.setValue(0);
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selected = list1.getSelectedValue().toString().split("->")[0];
                txtAreaDescripcionTour.setText(gestionTour.buscarTour(selected).getInformacionRelevante());
            }
        });
        listToursModificar.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selected = listToursModificar.getSelectedValue().toString().split("->")[0];
                txtModificarDetallesTour.setText(gestionTour.buscarTour(selected).getInformacionRelevante());

            }
        });

        btnEliminarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    reservaTourCancelar.cancelarReserva();
                    gestionReserva.removerReserva(reservaTourCancelar);
                    setReservasUsuario();
            }
        });
        boxCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcion = (String)boxCancelar.getSelectedItem();
                int numReserva = Integer.parseInt(opcion.split("-")[0]);



                reservaTourCancelar = gestionReserva.buscarReserva(numReserva);
                lblNumCancelar.setText("# de personas:");
                lblFechaCreacionCancelar.setText("Fecha de Creacion de Reserva:");
                lblFechaConfirmacionCancelar.setText("Fecha de Confirmacion de Reserva:");
                lblNumCancelar.setText(lblNumCancelar.getText() + " " + reservaTourCancelar.getNumeroPersonas());
                lblFechaCreacion.setText(lblFechaCreacionCancelar.getText() + " " + reservaTourCancelar.getFechaCreacion());
                lblFechaConfirmacion.setText(lblFechaConfirmacionCancelar.getText() + " " + reservaTourCancelar.getFechaConfirmacionPago());

                DefaultListModel toursEnReservaModel = new DefaultListModel<>();
                ArrayList<Tour> toursEnReservaList = reservaTourCancelar.getToursAgregados();

                for(Tour tour: toursEnReservaList) {
                    toursEnReservaModel.addElement(tour.getInformacionRelevante());
                }

                listaEliminar.setModel(toursEnReservaModel);
            }
        });
    }

    public boolean verificarTourYaAgregado(String opcionSelecionada, JList listaSeleccionados) {
        ListModel modeloLista = listaSeleccionados.getModel();

        for (int i = 0; i < modeloLista.getSize(); i++) {
            Object tour =  modeloLista.getElementAt(i);
            if(tour.toString().compareTo(opcionSelecionada) == 41 || tour.toString().compareTo(opcionSelecionada) == 0){
                JOptionPane.showMessageDialog(null,
                        "Este tour ya fue agregado",
                        "Gestor Reserva",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        return true;
    }

    public void setReservasUsuario() {
        String nombreUsuario = this.usuarioVerificado.getNombre();
        String apellidoUsuario = this.usuarioVerificado.getApellido();
        ArrayList<ReservaTour> reservas = this.gestionReserva.getReservaciones();

        for (ReservaTour reserva: reservas) {
            if ((reserva.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) && (reserva.getApellidoUsuario().equalsIgnoreCase(apellidoUsuario))) {


                String reservaStr = reserva.getNumReserva() + "-" + reserva.getNombreUsuario() + " " + reserva.getApellidoUsuario();

                // Agregamos la reserva al HashSet solo si no está duplicada
                if (reservasUnicas.add(reservaStr)) {
                    this.comboBox2.addItem(reservaStr);
                    this.comboBox3.addItem(reservaStr);
                    this.boxCancelar.addItem(reservaStr);
                }
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
        List<Tour> toursDispo = this.gestionTour.getToursDisponibles(sesion.getDestinoComun(), sesion.getFechaComun());

        for (Tour i : toursDispo){
            model.addElement(i.getNombre() + "->  Inicio: " + i.getFechaInicio());
        }

        list.setModel(model);
    }

    public void mostrarToursEnReserva(JList list){
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<Tour> tours = this.listaToursAgregadosReserva;

        for(Tour i: tours){
            model.addElement(i.getNombre() + "-> Inicia: " + i.getFechaInicio());
        }
        list.setModel(model);
    }

}
