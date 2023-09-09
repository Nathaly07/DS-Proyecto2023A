package Seguros;

import Principal.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InterfazCancelar extends JFrame{
    private JTable tblSeguros;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JPanel panelPrincipal;

    private GestorSeguros gestorSeguros = null;
    private Usuario cliente;

    public InterfazCancelar(){}

    public InterfazCancelar(Usuario cliente, GestorSeguros gestorSeguros){
        this.gestorSeguros = gestorSeguros;
        this.cliente = cliente;
        this.mostrarSeguros();

        // En resumen, se llama al gestor transmitido por la interfaz principal, y ejecuta la acción.
        this.btnEliminar.addActionListener((e) -> {
            int fila = this.tblSeguros.getSelectedRow();
            if(fila<0){
                JOptionPane.showMessageDialog(null, "¡No has seleccionado ningún seguro!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                int numSeguro = (int) this.tblSeguros.getValueAt(fila,0)-1;
                Seguro seguroEliminar = this.gestorSeguros.buscarSeguroEspecifico(numSeguro);
                int eleccion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el contrato?\nEl procedimiento no se puede revertir.", "Advertencia", JOptionPane.YES_NO_OPTION );
                if(eleccion==-1||eleccion==1){
                    JOptionPane.showMessageDialog(null, "Gracias por no hacerlo. Lo valoramos mucho.", "Buena decisión", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    this.gestorSeguros.eliminarSeguro(seguroEliminar);
                    this.setVisible(false);
                }
            }
        });

        this.btnCancelar.addActionListener((e) -> {
            this.setVisible(false);
        });

    }

    //Imprime TODOS los seguros del cliente.
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

            this.tblSeguros.setModel(model);
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

    public GestorSeguros getGestorSeguros() {
        return gestorSeguros;
    }

    public void setGestorSeguros(GestorSeguros gestorSeguros) {
        this.gestorSeguros = gestorSeguros;
    }
}
