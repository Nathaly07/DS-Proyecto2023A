package Seguros;

import Principal.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InterfazSeguro extends JFrame {
    private JTable tblSegurosCliente;
    private JButton btnNuevoSeguro;
    public JPanel panelPrincipal;
    private JButton btnRenovar;
    private JButton btnIndemnizar;
    private JButton btnCancelar;
    private JButton mostrarButton;
    private Usuario cliente;
    public GestorSeguros gestorSeguros;
    InterfazSeguroViaje interfazSeguroViaje = new InterfazSeguroViaje();
    InterfazSeguroMedico interfazSeguroMedico = new InterfazSeguroMedico();
    InterfazSeguroVida interfazSeguroVida = new InterfazSeguroVida();
    InterfazRenovar interfazRenovar = new InterfazRenovar();
    InterfazIndemnizar interfazIndemnizar = new InterfazIndemnizar();
    InterfazCancelar interfazCancelar = new InterfazCancelar();

    //Se pasa el cliente para saber cuales son sus seguros.
    public InterfazSeguro(Usuario cliente){
        this.gestorSeguros = new GestorSeguros();
        this.gestorSeguros.insertarSeguros(cliente);
        this.cliente = cliente;
        this.mostrarSeguros();



        this.btnNuevoSeguro.addActionListener((e) -> {
            //Array para mostrar las opciones al crear un nuevo seguro.
            String[] opcionesNuevoSeguro = new String[]{"Seguro de viaje", "Seguro de médico", "Seguro de vida"};

            //Dependiendo de que seleccione, se abrirá la ventana correspondiente.
            int tipoSeguro = JOptionPane.showOptionDialog((Component)null, "Por favor, indica que tipo de seguro deseas: ", "Nuevo seguro", -1, 3, (Icon)null, opcionesNuevoSeguro, opcionesNuevoSeguro[0]);
            if (tipoSeguro == -1) {
                JOptionPane.showMessageDialog((Component)null, "Es una pena. Vuelve si deseas un nuevo seguro.");
            }else if(tipoSeguro == 0){
                //Es necesario pasar el cliente, ya que, es necesario ligar el seguro con el usuario.
                this.interfazSeguroViaje.setCliente(cliente);
                interfazSeguroViaje.crearFrame();
            }else if(tipoSeguro == 1){
                this.interfazSeguroMedico.setCliente(cliente);
                this.interfazSeguroMedico.crearFrame();
            }else if(tipoSeguro == 2){
                this.interfazSeguroVida.setCliente(cliente);
                this.interfazSeguroVida.crearFrame();
            }
        });

        //Actualiza los datos de los seguros--------------IMPORTANTE????????????????????
        this.mostrarButton.addActionListener((e) -> {
            mostrarSeguros();
        });


        //Los otros botones que abren las ventanas. En su mayoria, necesitan el cliente
        // y el gestor de seguros, porque, dentro de cada interfaz se hace la acción con
        // el gestor. Y despues, se lo "devuelve" a esta interfaz.
        this.btnRenovar.addActionListener((e) -> {
            this.interfazRenovar = new InterfazRenovar(this.cliente, this.gestorSeguros);
            this.interfazRenovar.crearFrame();
        });

        this.btnIndemnizar.addActionListener((e) -> {
            this.interfazIndemnizar = new InterfazIndemnizar(this.cliente, this.gestorSeguros);
            this.interfazIndemnizar.crearFrame();
        });

        this.btnCancelar.addActionListener((e) -> {
            this.interfazCancelar = new InterfazCancelar(this.cliente, this.gestorSeguros);
            this.interfazCancelar.crearFrame();
        });


    }

    //Imprime todos los seguros del cliente.
    public void mostrarSeguros() {

        //IMPORTANTE------------ La forma para pasar los objetos entre interfaces es complicado :(
        // Fue la única forma que se me ocurrió. Esto se explica en el método.
        actualizarDatos();
        ArrayList<Seguro> seguros = this.gestorSeguros.buscarSegurosCliente(this.cliente);
        if(seguros!=null){
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("num_Seguro");
            model.addColumn("Fecha Inicio");
            model.addColumn("Fecha Vencimiento");
            model.addColumn("Tipo de Seguro");
            model.addColumn("Prima total");
            model.addColumn("Estado");
            for (Seguro seguro : seguros) {
                String tipoSeguro = "";
                if(seguro.getClass().toString().equals("class Seguros.SeguroDeViajes")){
                    tipoSeguro = "Seguro de viaje";
                } else if (seguro.getClass().toString().equals("class Seguros.SeguroDeVida")) {
                    tipoSeguro = "Seguro de vida";
                } else if (seguro.getClass().toString().equals("class Seguros.SeguroMédico")) {
                    tipoSeguro = "Seguro médico";
                }

                model.addRow(new Object[]{seguros.indexOf(seguro) + 1,
                        seguro.getFechaDeInicio().toString(),
                        seguro.getFechaDeVencimiento().toString(),
                        tipoSeguro,
                        seguro.calcularPrimaTotal(),
                        seguro.getEstado()});

            }

            this.tblSegurosCliente.setModel(model);
        }

    }

    //IMPORTANTE--------------- Cuando la interfaz externa crea un objeto seguro,
    // o hace un cambio en el gestor que fue transmitido por esta interfaz,
    // será necesario que lo devuelva aquí, pero, no es sencillo hacer que regrese
    // de forma "automática" o al finalizar la acción. Por eso, cada vez que hacemos
    // una acción como crear un seguro, renovarlo, etc. SE DEBE PRESIONAR EL BOTÓN de
    // "mostrar", de modo que, los cambios se registren en este gestor de seguros.
    public void actualizarDatos(){
        if(!this.interfazSeguroViaje.getSeguroDeViajes().getEstado().equalsIgnoreCase("Inactivo")){
            this.gestorSeguros.agregarSeguro(this.interfazSeguroViaje.getSeguroDeViajes());
            this.interfazSeguroViaje.setSeguroDeViajes(new SeguroDeViajes("Inactivo"));
        }
        if (!this.interfazSeguroMedico.getSeguroMédico().getEstado().equalsIgnoreCase("Inactivo")) {
            this.gestorSeguros.agregarSeguro(this.interfazSeguroMedico.getSeguroMédico());
            this.interfazSeguroMedico.setSeguroMédico(new SeguroMédico("Inactivo"));
        }
        if (!this.interfazSeguroVida.getSeguroDeVida().getEstado().equalsIgnoreCase("Inactivo")) {
            this.gestorSeguros.agregarSeguro(this.interfazSeguroVida.getSeguroDeVida());
            this.interfazSeguroVida.setSeguroDeVida(new SeguroDeVida("Inactivo"));
        }
        if(this.interfazRenovar.getGestorSeguros()!=null){
            this.gestorSeguros = this.interfazRenovar.getGestorSeguros();
            this.interfazRenovar.setGestorSeguros(null);
        }
        if(this.interfazIndemnizar.getGestorSeguros()!=null){
            this.gestorSeguros = this.interfazIndemnizar.getGestorSeguros();
            this.interfazIndemnizar.setGestorSeguros(null);
        }
        if(this.interfazCancelar.getGestorSeguros()!=null){
            this.gestorSeguros = this.interfazCancelar.getGestorSeguros();
            this.interfazCancelar.setGestorSeguros(null);
        }
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
    }

}
