package Seguros;

import Principal.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class InterfazIndemnizar extends JFrame {
    private JTable tblSeguros;
    private JButton btnIndemnizar;
    private JButton btnCancelar;
    private JPanel panelPrincipal;

    public InterfazIndemnizar(InterfazSeguro interfazSeguro){
        this.mostrarSeguros(interfazSeguro);

        //Se busca al seguro por medio de su ID de la tabla. Y despues ejecuta la acción.
        this.btnIndemnizar.addActionListener((e) -> {
            int fila = this.tblSeguros.getSelectedRow();
            if(fila<0){
                JOptionPane.showMessageDialog(null, "¡No has seleccionado ningún seguro!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String motivo = JOptionPane.showInputDialog(null, "Por favor, ingresa tu motivo para solicitar la indemnización: ", "Motivo", JOptionPane.QUESTION_MESSAGE);
                float valorGastado = Float.parseFloat(JOptionPane.showInputDialog(null, "¿Cuánto gastaste en el proceso?: ", "Valor gastado", JOptionPane.QUESTION_MESSAGE));

                int numSeguro = (int) this.tblSeguros.getValueAt(fila,0)-1;
                interfazSeguro.gestorSeguros.buscarSeguroEspecifico(numSeguro).indemnizar(valorGastado, motivo);

                this.setVisible(false);

            }
        });

        this.btnCancelar.addActionListener((e) -> {
            this.setVisible(false);
        });
    }

    //Imprime solo los seguros activos del cliente. De modo que, no puede gozar la indemnización
    // de seguros inactivos o ya cobrados.
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
                if(seguro.getEstado().equalsIgnoreCase("Activo")){
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

            this.tblSeguros.setModel(model);
        }

    }

    public void crearFrame() {
        setTitle("Proceso de indemnización");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
    }
}
