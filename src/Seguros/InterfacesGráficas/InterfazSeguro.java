package Seguros.InterfacesGráficas;

import Principal.Usuario;
import Seguros.GestorSeguros;
import Seguros.Seguro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class InterfazSeguro extends JFrame {
    private JTable tblSegurosCliente;
    private JButton btnNuevoSeguro;
    public JPanel panelPrincipal;
    private JButton btnRenovar;
    private JButton btnIndemnizar;
    private JButton btnCancelar;
    public Usuario cliente;
    public GestorSeguros gestorSeguros;

    //Se pasa el cliente para saber cuales son sus seguros.
    public InterfazSeguro(Usuario cliente){
        /*------------------------------------------ICONOS DE LOS BOTONES-----------------------------*/
        this.colocarIconos("src/Seguros/InterfacesGráficas/Imagenes/IconoNuevo.png", this.btnNuevoSeguro);
        this.colocarIconos("src/Seguros/InterfacesGráficas/Imagenes/IconoRenovar.png", this.btnRenovar);
        this.colocarIconos("src/Seguros/InterfacesGráficas/Imagenes/IconoIndemnizar.png", this.btnIndemnizar);
        this.colocarIconos("src/Seguros/InterfacesGráficas/Imagenes/IconoCancelar.png", this.btnCancelar);


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
                InterfazSeguroViaje interfazSeguroViaje = new InterfazSeguroViaje(this);
                interfazSeguroViaje.crearFrame();
            }else if(tipoSeguro == 1){
                InterfazSeguroMedico interfazSeguroMedico = new InterfazSeguroMedico(this);
                interfazSeguroMedico.crearFrame();
            }else if(tipoSeguro == 2){
                InterfazSeguroVida interfazSeguroVida = new InterfazSeguroVida(this);
                interfazSeguroVida.crearFrame();
            }
        });

        //Los otros botones que abren las ventanas. Solo necesitan esta interfaz, porque, es
        // el que almacena al cliente y al gestor.
        this.btnRenovar.addActionListener((e) -> {
            InterfazRenovar interfazRenovar = new InterfazRenovar(this);
            interfazRenovar.crearFrame();
        });

        this.btnIndemnizar.addActionListener((e) -> {
            InterfazIndemnizar interfazIndemnizar = new InterfazIndemnizar(this);
            interfazIndemnizar.crearFrame();
        });

        this.btnCancelar.addActionListener((e) -> {
            InterfazCancelar interfazCancelar = new InterfazCancelar(this);
            interfazCancelar.crearFrame();
        });



    }

    //Imprime todos los seguros del cliente.
    public void mostrarSeguros() {
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
            this.tblSegurosCliente.setDefaultRenderer(Object.class, new RenderColores());
        }

    }

    public void colocarIconos(String direccion, JButton boton){
        ImageIcon iconoNuevo = new ImageIcon(direccion);
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setIconTextGap(10);
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
