package Seguros;

import Principal.Usuario;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class InterfazSeguroVida extends JFrame {
    private JButton btnAgregarBeneficiario;
    private JTextArea txtBeneficiario;
    private JComboBox comboNivelSeguro;
    private JButton btnNuevoSeguro;
    private JPanel panelPrincipal;
    private JButton btnCancelar;
    private SeguroDeVida seguroDeVida = new SeguroDeVida("Inactivo");
    private Usuario cliente;

    public InterfazSeguroVida(){
        this.btnNuevoSeguro.addActionListener((e) -> {
            //Se extraen los beneficiarios-------------------------
            StringTokenizer tokens=new StringTokenizer(this.txtBeneficiario.getText(), "\n");
            String[]beneficiarios = new String[tokens.countTokens()-1];
            int i = 0;
            while(tokens.hasMoreTokens()){
                String beneficiario = tokens.nextToken();
                if(!beneficiario.equalsIgnoreCase("-- Beneficiarios inscritos")){
                    beneficiarios[i] = beneficiario;
                    i++;
                }
            }

            //Se extrae la fecha actual y la de vencimiento-------------------------
            Date fechaActual = new Date();
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaActual);
            calendario.add(Calendar.MONTH, 1);
            Date fechaVencimiento = calendario.getTime();

            //Se extrae el nivel del seguro--------------------------
            int nivelSeguro = this.comboNivelSeguro.getSelectedIndex();

            //Se crea el seguro-------------------------------
            this.seguroDeVida = new SeguroDeVida(this.cliente, beneficiarios, fechaActual, fechaVencimiento, nivelSeguro, "Activo" );
            JOptionPane.showMessageDialog(null, "Tu seguro de VIDA se ha creado exitosamente.", "Éxito", JOptionPane.WARNING_MESSAGE);
            this.setVisible(false);
        });

        this.btnAgregarBeneficiario.addActionListener((e) -> {
            String beneficiario = JOptionPane.showInputDialog(null, "Por favor, ingresa el NOMBRE de tu beneficiario", "Nuevo beneficiario", JOptionPane.QUESTION_MESSAGE);
            if(beneficiario != null){
                this.txtBeneficiario.setText(this.txtBeneficiario.getText()+"\n"+beneficiario);
            }
        });
        this.btnCancelar.addActionListener((e) -> {
            this.setVisible(false);
        });

    }

    public void crearFrame() {
        this.limpiarEspacio();
        setTitle("Nuevo seguro de vida");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(this.panelPrincipal);
        setVisible(true);
    }

    public void limpiarEspacio(){
        this.txtBeneficiario.setText("-- Beneficiarios inscritos");
        this.comboNivelSeguro.setSelectedIndex(0);
        this.seguroDeVida = new SeguroDeVida("Inactivo");
    }

    public void setSeguroDeVida(SeguroDeVida seguroDeVida) {
        this.seguroDeVida = seguroDeVida;
    }

    public SeguroDeVida getSeguroDeVida() {
        return seguroDeVida;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

}
