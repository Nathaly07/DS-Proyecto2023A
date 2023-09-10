package Seguros;

import Principal.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InterfazRenovar extends JFrame {
    private JTable tblSegurosRenovar;
    private JButton btnRenovar;
    private JButton btnCancelar;
    private JPanel panelPrincipal;
    private JScrollPane scrollPane;


    public InterfazRenovar(InterfazSeguro interfazSeguro){
        this.mostrarSeguros(interfazSeguro);

        //Busca al seguro con el ID de la tabla y ejecuta la acción.
        this.btnRenovar.addActionListener((e) -> {
            int fila = this.tblSegurosRenovar.getSelectedRow();
            if(fila<0){
                JOptionPane.showMessageDialog(null, "¡No has seleccionado ningún seguro!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                int numSeguro = (int) this.tblSegurosRenovar.getValueAt(fila,0)-1;
                int eleccion = JOptionPane.showConfirmDialog(null, "Esta acción requiere de un pago.\n¿Desea continuar?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if(eleccion==-1||eleccion==1){
                    JOptionPane.showMessageDialog(null, "Es una pena. \nVuelve si deseas renovar tu seguro.", "Cancelar renovación", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    String[] opcionesPago = new String[]{"Paypal", "Transferencia", "Tarjeta de crédito"};
                    int tipoPago = JOptionPane.showOptionDialog((Component)null, "Por favor, indica tu forma de pago: ", "Pagar", -1, 3, (Icon)null, opcionesPago, opcionesPago[0]);
                    interfazSeguro.gestorSeguros.buscarSeguroEspecifico(numSeguro).pagar(opcionesPago[tipoPago]);
                    interfazSeguro.gestorSeguros.buscarSeguroEspecifico(numSeguro).newRenovar();
                    this.setVisible(false);
                }
            }
        });

        this.btnCancelar.addActionListener((e) -> {
            this.setVisible(false);
        });

    }

    //Solo muestra los seguros inactivos del cliente. Es para que pueda pagar a aquellos que
    // no han sido renovados.
    public void mostrarSeguros(InterfazSeguro interfazSeguro) {
        ArrayList<Seguro> seguros = interfazSeguro.gestorSeguros.buscarSegurosCliente(interfazSeguro.cliente);
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
                if(seguro.getEstado().equalsIgnoreCase("Inactivo")){
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

            }

            this.tblSegurosRenovar.setModel(model);
        }

    }

    public void crearFrame() {
        setTitle("Renovar seguro");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
    }

}
