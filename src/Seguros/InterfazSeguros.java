package Seguros;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class InterfazSeguros extends JFrame {
    public JPanel pnlOpcionesSeguro;
    private JTabbedPane tbdOpcionesSeguro;
    private JPanel pnlCrearSeguro;
    private JPanel pnlRenovarSeguro;
    private JPanel pnlPagarSeguro;
    private JPanel pnlIndemnización;
    private JPanel pnlCancelarSeguro;
    private JTextField txtPropietario;
    private JTextField txtCondiciones;
    private JTextField txtPrima;
    private JTextField txtBeneficiarios;
    private JTextField txtFechaInicio;
    private JButton btnCrearSeguro;
    private JComboBox cmbFechaVencimiento;
    private JTextField txtFechaInicioMes;
    private JTextField txtFechaInicioAño;
    private JTextField txtFechaInicioDia;
    private JTextField txtSeguroCancelar;
    private JButton btnCancelarSeguro;
    private JButton btnRenovar;
    private JTextField txtFechaRenovación;
    private JLabel lblFechaDeVencimiento;
    private JTabbedPane tbdTiposDeSeguro;
    private JTextField txtDestino;
    private JTextField txtCoberturasViajes500;
    private JTextField txtCoberturasMédico;
    private JTextField txtMontoAPagar;
    private JButton pagarButton;
    private JTextField txtFechaPago;
    private JTextField txtValorGastado;
    private JTextField txtMotivo;
    private JButton btnIndemnizar;
    private JTextField txtProp;
    private JTextField txtCoberturasViajes1000;
    private JTextField txtCoberturasViajes5000;
    private JPanel pnlFechaInicio;
    private JTable tblSeguros;
    private JButton btnDesplegarSeguros;
    private JLabel lblProp;
    private JTextField txtNivel;
    private JLabel lblNivel;
    private JLabel lblNivelSeguroDeVida;
    private JTextField txtNivelSeguroDeVida;
    private JComboBox cmbTiposDePago;
    private JLabel lblMontoAPagar;
    private GestorSeguros gestorSeguros = new GestorSeguros();
    private JDateChooser fechaInicioSeguro = new JDateChooser();


    public InterfazSeguros() {
        pnlFechaInicio.add(fechaInicioSeguro);

        btnCrearSeguro.addActionListener(e -> {
            sumarMesesAFecha(fechaInicioSeguro.getDate(), cmbFechaVencimiento.getSelectedIndex());

            Date fechaInicio = fechaInicioSeguro.getDate();
            Date fechaVencimiento = new Date(lblFechaDeVencimiento.getText());
            int destino = Integer.parseInt(txtDestino.getText());
            float primaSinRecargo = Float.parseFloat(txtPrima.getText());
            String[] condiciones = txtCondiciones.getText().split(",");
            String[] beneficiarios = txtBeneficiarios.getText().split(",");

            if (tbdTiposDeSeguro.getSelectedIndex() == 0) {
                String[] coberturas500 = txtCoberturasViajes500.getText().split(",");
                String[] coberturas1000 = txtCoberturasViajes1000.getText().split(",");
                String[] coberturas5000 = txtCoberturasViajes5000.getText().split(",");
                int numCoberturas = coberturas5000.length + coberturas1000.length + coberturas5000.length;
                String[][] coberturas = new String[numCoberturas][2];
                int i;
                for (i = 0; i < coberturas500.length; i++) {
                    coberturas[i][0] = coberturas500[i];
                    coberturas[i][1] = "500";
                }
                int j, a = 0;

                for (j = i; j < i + coberturas1000.length; j++) {
                    coberturas[j][0] = coberturas1000[a];
                    coberturas[j][1] = "1000";
                    a++;
                }
                int k;
                a = 0;
                for (k = j; k < j + coberturas5000.length; k++) {
                    coberturas[k][0] = coberturas5000[a];
                    coberturas[k][1] = "5000";
                    a++;
                }

                Seguro seguroDeViajes = new SeguroDeViajes(
                        txtPropietario.getText(),
                        condiciones,
                        beneficiarios,
                        fechaInicio,
                        fechaVencimiento,
                        primaSinRecargo,
                        destino,
                        coberturas
                );
                this.gestorSeguros.procesarCreaciónSeguro(seguroDeViajes);
            } else if (tbdTiposDeSeguro.getSelectedIndex() == 1) {
                String[] coberturas = txtCoberturasMédico.getText().split(",");
                Seguro seguroMédico = new SeguroMédico(
                        txtPropietario.getText(),
                        condiciones,
                        beneficiarios,
                        fechaInicio,
                        fechaVencimiento,
                        primaSinRecargo,
                        coberturas,
                        Integer.parseInt(txtNivel.getText())
                );
                this.gestorSeguros.procesarCreaciónSeguro(seguroMédico);
            } else if (tbdTiposDeSeguro.getSelectedIndex() == 2) {
                Seguro seguroDeVida = new SeguroDeVida(
                        txtPropietario.getText(),
                        condiciones,
                        beneficiarios,
                        fechaInicio,
                        fechaVencimiento,
                        primaSinRecargo,
                        Integer.parseInt(txtNivel.getText())
                );
                this.gestorSeguros.procesarCreaciónSeguro(seguroDeVida);
            }
        });

        btnCancelarSeguro.addActionListener(e -> {
            int numSeguro = tblSeguros.getSelectedRow();
            if (numSeguro < 0) {
                JOptionPane.showMessageDialog(null, "No has seleccionado ningún seguro.");
            } else {
                int indiceSeguro = Integer.parseInt(tblSeguros.getValueAt(numSeguro, 0).toString()) - 1;
                Seguro seguro = gestorSeguros.buscarSeguro(txtProp.getText()).get(indiceSeguro);
                gestorSeguros.eliminarSeguro(seguro);
                btnDesplegarSeguros.doClick();
            }
        });

        btnRenovar.addActionListener(e -> {
            int numSeguro = tblSeguros.getSelectedRow();
            if (numSeguro < 0) {
                JOptionPane.showMessageDialog(null, "No has seleccionado ningún seguro.");
            } else {
                int indiceSeguro = Integer.parseInt(tblSeguros.getValueAt(numSeguro, 0).toString()) - 1;
                gestorSeguros.buscarSeguro(txtProp.getText()).get(indiceSeguro).renovar((new Date(txtFechaRenovación.getText())));
                btnDesplegarSeguros.doClick();
            }
        });

        btnIndemnizar.addActionListener(e -> {
            int numSeguro = tblSeguros.getSelectedRow();
            if (numSeguro < 0) {
                JOptionPane.showMessageDialog(null, "No has seleccionado ningún seguro.");
            } else {
                float montoPedido = Float.parseFloat(this.txtValorGastado.getText());
                int indiceSeguro = Integer.parseInt(tblSeguros.getValueAt(numSeguro, 0).toString()) - 1;
                gestorSeguros.buscarSeguro(txtProp.getText()).get(indiceSeguro).indemnizar(montoPedido, this.txtMotivo.getText());
            }
        });

        pagarButton.addActionListener(e -> {
            int numSeguro = tblSeguros.getSelectedRow();

            if (numSeguro < 0) {
                JOptionPane.showMessageDialog(null, "No has seleccionado ningún seguro.");
            } else{
                int indiceSeguro = Integer.parseInt(tblSeguros.getValueAt(numSeguro, 0).toString()) - 1;
                Seguro seguroPagar=gestorSeguros.buscarSeguro(txtProp.getText()).get(indiceSeguro);
                seguroPagar.pagar(Double.parseDouble(txtMontoAPagar.getText()), Objects.requireNonNull(cmbTiposDePago.getSelectedItem()).toString(),
                        new Date(txtFechaPago.getText()));
            }
        });

        btnDesplegarSeguros.addActionListener(e -> {
            ArrayList<Seguro> seguros = gestorSeguros.buscarSeguro(txtProp.getText());
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("num_Seguro");
            model.addColumn("Fecha Inicio");
            model.addColumn("Fecha Vencimiento");
            model.addColumn("Tipo de Seguro");
            model.addColumn("Prima");

            for (Seguro seguro : seguros) {
                model.addRow(new Object[]{seguros.indexOf(seguro) + 1,
                        seguro.getFechaDeInicio().toString(),
                        seguro.getFechaDeVencimiento().toString(),
                        seguro.getClass().toString(),
                        seguro.getPrimaSinRecargo()});
            }

            tblSeguros.setModel(model);
        });
    }

    private void sumarMesesAFecha(Date fechaCalendar, int meses) {
        int númeroMesASumar = 0;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("d MMM y");

        if (meses == 1) {
            númeroMesASumar = 3;
        } else if (meses == 2) {
            númeroMesASumar = 6;
        } else if (meses == 3) {
            númeroMesASumar = 12;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCalendar);
        calendar.add(Calendar.MONTH, númeroMesASumar);
        Date fechaFinal = calendar.getTime();
        String fechaVencimiento = formatoDeFecha.format(fechaFinal);
        lblFechaDeVencimiento.setText(fechaVencimiento);
    }
}
