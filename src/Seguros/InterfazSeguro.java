package Seguros;

import Principal.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InterfazSeguro {
    private JTable tblSegurosCliente;
    private JButton btnNuevoSeguro;
    private JButton btnRenovarSeguro;
    private JButton btnIndemnizar;
    private JButton btnCancelar;
    private Usuario cliente;
    private GestorSeguros gestorSeguros;

    public InterfazSeguro(Usuario cliente){
        this.gestorSeguros = new GestorSeguros();
        this.cliente = cliente;
        String[] opcionesNuevoSeguro = new String[]{"Seguro de viaje", "Seguro de médico", "Seguro de vida"};
        this.btnNuevoSeguro.addActionListener((e) -> {
            int tipoSeguro = JOptionPane.showOptionDialog((Component)null, "Por favor, indica que tipo de seguro deseas: ", "Nuevo seguro", -1, 3, (Icon)null, opcionesNuevoSeguro, opcionesNuevoSeguro[0]);
            if (tipoSeguro == -1) {
                JOptionPane.showMessageDialog((Component)null, "Es una pena. Vuelve si deseas un nuevo seguro.");
            }

        });
    }
    public void mostrarSeguros() {
        ArrayList<Seguro> seguros = this.gestorSeguros.buscarSegurosCliente(this.cliente);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("num_Seguro");
        model.addColumn("Fecha Inicio");
        model.addColumn("Fecha Vencimiento");
        model.addColumn("Tipo de Seguro");
        model.addColumn("Prima total");
        Iterator var3 = seguros.iterator();

        while(var3.hasNext()) {
            Seguro seguro = (Seguro)var3.next();
            model.addRow(new Object[]{seguros.indexOf(seguro) + 1, seguro.getFechaDeInicio().toString(), seguro.getFechaDeVencimiento().toString(), seguro.getClass().toString(), seguro.calcularPrimaTotal()});
        }

        this.tblSegurosCliente.setModel(model);
    }

}
