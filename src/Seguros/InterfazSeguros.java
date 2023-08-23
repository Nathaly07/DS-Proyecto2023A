package Seguros;

import javax.swing.*;
import java.util.Date;

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
    private JLabel lblID;
    private JTextField txtFechaInicioMes;
    private JTextField txtFechaInicioAño;
    private JTextField txtFechaInicioDia;
    private JTextField txtSeguroCancelar;
    private JButton btnCancelarSeguro;
    private JButton btnRenovar;
    private JTextField txtFechaRenovación;
    private JTextField txtIDSeguro;
    private JLabel lblFechaDeVencimiento;
    private JTabbedPane tbdTiposDeSeguro;
    private JTextField txtDestino;
    private JTextField txtCoberturasViajes500;
    private JTextField txtCoberturasMédico;
    private JTextField txtMontoAPagar;
    private JButton pagarButton;
    private JTextField txtIDSeguroPago;
    private JTextField txtFechaPago;
    private JTextField txtValorGastado;
    private JTextField txtMotivo;
    private JButton btnIndemnizar;
    private JTextField textField1;
    private JTextField txtCoberturasViajes1000;
    private JTextField txtCoberturasViajes5000;
    private ModuloSeguros moduloSeguro = new ModuloSeguros();

    public InterfazSeguros() {
        btnCrearSeguro.addActionListener(e -> {
            int ID_Seguro = (int) (Math.random() * 5000 + 1000);
            lblID.setText(String.valueOf(ID_Seguro));

            String dia = "", mes = "", año = "";
            if (cmbFechaVencimiento.getSelectedIndex() == 1) {
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 9) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "12";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 10) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "1";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 11) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "2";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 12) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "3";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) < 9) {
                    mes = Integer.toString(Integer.parseInt(txtFechaInicioMes.getText()) + 3);
                    año = txtFechaInicioAño.getText();
                }
                dia = txtFechaInicioDia.getText();
            }
            if (cmbFechaVencimiento.getSelectedIndex() == 2) {
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 6) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "12";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 7) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "1";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 8) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "2";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 9) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "3";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 10) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "4";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 11) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "5";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) == 12) {
                    año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                    mes = "6";
                }
                if (Integer.parseInt(txtFechaInicioMes.getText()) < 6) {
                    mes = Integer.toString(Integer.parseInt(txtFechaInicioMes.getText()) + 6);
                    año = txtFechaInicioAño.getText();
                }
                dia = txtFechaInicioDia.getText();
            }
            if (cmbFechaVencimiento.getSelectedIndex() == 3) {
                mes = txtFechaInicioMes.getText();
                año = Integer.toString(Integer.parseInt(txtFechaInicioAño.getText()) + 1);
                dia = txtFechaInicioDia.getText();
            }
            lblFechaDeVencimiento.setText(dia + "/" + mes + "/" + año);

            Date fechaInicio = new Date(txtFechaInicioDia.getText() + "/" + txtFechaInicioMes.getText() + "/" + txtFechaInicioAño.getText());
            Date fechaVencimiento = new Date(lblFechaDeVencimiento.getText());
            int ID = Integer.parseInt(lblID.getText());
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
                        ID,
                        txtPropietario.getText(),
                        condiciones,
                        beneficiarios,
                        fechaInicio,
                        fechaVencimiento,
                        primaSinRecargo,
                        destino,
                        coberturas
                );
                this.moduloSeguro.procesarCreaciónSeguro(seguroDeViajes);
            } else if (tbdTiposDeSeguro.getSelectedIndex() == 1) {
                String[] coberturas = txtCoberturasMédico.getText().split(",");
                Seguro seguroMédico = new SeguroMédico(
                        ID,
                        txtPropietario.getText(),
                        condiciones,
                        beneficiarios,
                        fechaInicio,
                        fechaVencimiento,
                        primaSinRecargo,
                        coberturas
                );
                this.moduloSeguro.procesarCreaciónSeguro(seguroMédico);
            } else if (tbdTiposDeSeguro.getSelectedIndex() == 2) {
                float montoAPagar = Float.parseFloat(txtMontoAPagar.getText());
                Seguro seguroDeVida = new SeguroDeVida(
                        ID,
                        txtPropietario.getText(),
                        condiciones,
                        beneficiarios,
                        fechaInicio,
                        fechaVencimiento,
                        primaSinRecargo,
                        montoAPagar
                );
                this.moduloSeguro.procesarCreaciónSeguro(seguroDeVida);
            }
        });

        btnCancelarSeguro.addActionListener(e -> {
            int ID_Seguro = Integer.parseInt(this.txtSeguroCancelar.getText());
            this.moduloSeguro.procesarCancelarSeguro(ID_Seguro);

        });

        btnRenovar.addActionListener(e -> {
            int ID_Seguro = Integer.parseInt(this.txtIDSeguro.getText());
            Date fecha = new Date(this.txtFechaRenovación.getText());
            this.moduloSeguro.procesarRenovarSeguro(ID_Seguro, fecha);
        });

        btnIndemnizar.addActionListener(e -> {
            int ID_Seguro = Integer.parseInt(this.textField1.getText());
            float montoPedido = Float.parseFloat(this.txtValorGastado.getText());
            this.moduloSeguro.procesarIndemnización(ID_Seguro, this.txtMotivo.getText(), montoPedido);
        });

        pagarButton.addActionListener(e -> {
            int ID_Seguro = Integer.parseInt(this.txtIDSeguroPago.getText());
            Date fecha = new Date(this.txtFechaPago.getText());
            this.moduloSeguro.procesarPagoSeguro(ID_Seguro, fecha);
        });


    }

}
