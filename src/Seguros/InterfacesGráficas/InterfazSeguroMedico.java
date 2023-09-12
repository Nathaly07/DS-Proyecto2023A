package Seguros.InterfacesGráficas;

import Principal.Sesion;
import Seguros.SeguroMédico;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class InterfazSeguroMedico extends JFrame {
    private JButton btnAgregarBeneficiario;
    private JTextArea txtBeneficiarios;
    private JComboBox comboNivelSeguro;
    private JButton btnAgregarCobertura;
    private JTextArea txtCoberturas;
    private JPanel panelPrincipal;
    private JButton btnNuevoSeguro;
    private JButton btnCancelar;


    public InterfazSeguroMedico(InterfazSeguro interfazSeguro){
        interfazSeguro.colocarIconos("src/Seguros/InterfacesGráficas/Imagenes/IconoSeguroMedico.png", this.btnNuevoSeguro);
        interfazSeguro.colocarIconos("src/Seguros/InterfacesGráficas/Imagenes/IconoCancelarAccion.png", this.btnCancelar);


        this.btnNuevoSeguro.addActionListener((e) -> {
            //Se extraen los beneficiarios-------------------------
            StringTokenizer tokens=new StringTokenizer(this.txtBeneficiarios.getText(), "\n");
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
            Date fechaActual;
            if(Sesion.getInstance().getFechaComun()!=null){
                fechaActual= Sesion.getInstance().getFechaComun();
            }else{
                fechaActual= new Date();
            }
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaActual);
            calendario.add(Calendar.MONTH, 1);
            Date fechaVencimiento = calendario.getTime();

            //Se extrae el nivel del seguro médico
            int nivelSeguro = this.comboNivelSeguro.getSelectedIndex();

            //Se extrae las coberturas
            StringTokenizer tokensC=new StringTokenizer(this.txtCoberturas.getText(), "\n");
            String[]coberturas = new String[tokensC.countTokens()-1];
            i = 0;
            while(tokensC.hasMoreTokens()) {
                String cobertura = tokensC.nextToken();
                if (!cobertura.equalsIgnoreCase("-- Coberturas agregadas")) {
                    coberturas[i] = cobertura;
                    i++;
                }
            }

            //Creamos el seguro
            SeguroMédico seguroMédico = new SeguroMédico(interfazSeguro.cliente, beneficiarios, fechaActual, fechaVencimiento, coberturas, nivelSeguro, "Activo");
            interfazSeguro.gestorSeguros.agregarSeguro(seguroMédico);
            JOptionPane.showMessageDialog(null, "Tu seguro MÉDICO se ha creado exitosamente.", "Éxito", JOptionPane.WARNING_MESSAGE);
            interfazSeguro.mostrarSeguros();
            this.setVisible(false);

        });

        this.btnAgregarBeneficiario.addActionListener((e) -> {
            String beneficiario = JOptionPane.showInputDialog(null, "Por favor, ingresa el NOMBRE de tu beneficiario", "Nuevo beneficiario", JOptionPane.QUESTION_MESSAGE);
            if(beneficiario != null){
                this.txtBeneficiarios.setText(this.txtBeneficiarios.getText()+"\n"+beneficiario);
            }
        });

        this.btnAgregarCobertura.addActionListener((e) -> {
            String cobertura = JOptionPane.showInputDialog(null,
                    "Por favor, ingresa el NÚMERO de la cobertura a cubrir en el seguro médico:\n"
                            +"1.- Accidente menor\n"
                            +"2.- Accidente mayor\n"
                            +"3.- Enfermedad de bajo nivel\n"
                            +"4.- Enfermedad de alto nivel\n","Agregar cobertura", JOptionPane.QUESTION_MESSAGE);
            if(cobertura != null){
                int numCobertura = Integer.parseInt(cobertura);
                if(numCobertura<0||numCobertura>4){
                    JOptionPane.showMessageDialog(null, "Esa no es una opción válida.", "ERROR 401",JOptionPane.ERROR_MESSAGE);
                }else{
                    switch (numCobertura){
                        case 1:{
                            this.txtCoberturas.setText(this.txtCoberturas.getText()+"\nAccidente menor");
                            break;
                        }
                        case 2:{
                            this.txtCoberturas.setText(this.txtCoberturas.getText()+"\nAccidente mayor");
                            break;
                        }
                        case 3:{
                            this.txtCoberturas.setText(this.txtCoberturas.getText()+"\nEnfermedad de bajo nivel");
                            break;
                        }
                        case 4:{
                            this.txtCoberturas.setText(this.txtCoberturas.getText()+"\nEnfermedad de alto nivel");
                            break;
                        }
                    }

                }

            }

        });

        this.btnCancelar.addActionListener((e) -> {
            this.setVisible(false);
        });

    }

    public void crearFrame() {
        setTitle("Nuevo seguro médico");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(this.panelPrincipal);
        setVisible(true);
    }
}
