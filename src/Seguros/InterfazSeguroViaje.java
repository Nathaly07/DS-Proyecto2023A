package Seguros;

import javax.swing.*;
import Principal.Usuario;

import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Date;
public class InterfazSeguroViaje extends JFrame {
    public JPanel panelSeguroViaje;
    private JPanel subPanel01;
    private JButton btnAgregarBeneficiario;
    private JTextArea areaBeneficiarios;
    private JComboBox comboBoxTipoViaje;
    private JButton btnAgregarCobertura;
    private JTextArea areaCoberturasAgregadas;
    private JButton btnCrearSeguro;
    private SeguroDeViajes seguro;
    private GestorSeguros gestor;


    public InterfazSeguroViaje(Usuario cliente, GestorSeguros gestor){
        this.gestor = gestor;
        this.btnCrearSeguro.addActionListener((e) -> {
            //Se extraen los beneficiarios-------------------------
            StringTokenizer tokens=new StringTokenizer(this.areaBeneficiarios.getText(), "\n");
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

            //Se extrae el tipo de destino
            int tipoDestino = this.comboBoxTipoViaje.getSelectedIndex();

            //Se extrae las coberturas
            StringTokenizer tokensC=new StringTokenizer(this.areaCoberturasAgregadas.getText(), "\n");
            String[][]coberturas = new String[tokensC.countTokens()-1][2];
            i = 0;
            while(tokensC.hasMoreTokens()) {
                String cobertura = tokensC.nextToken();
                if (!cobertura.equalsIgnoreCase("-- Coberturas agregadas")) {
                    switch (cobertura) {
                        case "Robo de pertenencias": {
                            coberturas[i][0] = cobertura;
                            coberturas[i][1] = "200";
                            break;
                        }
                        case "Accidente en el viaje": {
                            coberturas[i][0] = cobertura;
                            coberturas[i][1] = "1000";
                            break;
                        }
                        case "Cancelación del viaje": {
                            coberturas[i][0] = cobertura;
                            coberturas[i][1] = "250";
                            break;
                        }
                        case "Reembolso de hospedaje": {
                            coberturas[i][0] = cobertura;
                            coberturas[i][1] = "260";
                            break;
                        }
                    }
                    i++;
                }
            }
            //Creamos el seguro-----------------------------
            this.seguro = new SeguroDeViajes(cliente,beneficiarios,fechaActual, fechaVencimiento, tipoDestino, coberturas, 1);
            this.gestor.agregarSeguro(this.seguro);
            this.setVisible(false);

        });


        this.btnAgregarBeneficiario.addActionListener((e) -> {
            String beneficiario = JOptionPane.showInputDialog(null, "Por favor, ingresa el NOMBRE de tu beneficiario", "Nuevo beneficiario", JOptionPane.QUESTION_MESSAGE);
            if(beneficiario != null){
                this.areaBeneficiarios.setText(this.areaBeneficiarios.getText()+"\n"+beneficiario);
            }
        });

        this.btnAgregarCobertura.addActionListener((e) -> {
            String cobertura = JOptionPane.showInputDialog(null,
                    "Por favor, ingresa el NÚMERO de la cobertura a cubrir en el viaje:\n"
                            +"1.- Robo de pertenencias\n"
                            +"2.- Accidente en el viaje\n"
                            +"3.- Cancelación del viaje\n"
                            +"4.- Reembolso de hospedaje\n","Agregar cobertura", JOptionPane.QUESTION_MESSAGE);
            if(cobertura != null){
                int numCobertura = Integer.parseInt(cobertura);
                if(numCobertura<0||numCobertura>4){
                    JOptionPane.showMessageDialog(null, "Esa no es una opción válida.", "ERROR 401",JOptionPane.ERROR_MESSAGE);
                }else{
                    switch (numCobertura){
                        case 1:{
                            this.areaCoberturasAgregadas.setText(this.areaCoberturasAgregadas.getText()+"\nRobo de pertenencias");
                            break;
                        }
                        case 2:{
                            this.areaCoberturasAgregadas.setText(this.areaCoberturasAgregadas.getText()+"\nAccidente en el viaje");
                            break;
                        }
                        case 3:{
                            this.areaCoberturasAgregadas.setText(this.areaCoberturasAgregadas.getText()+"\nCancelación del viaje");
                            break;
                        }
                        case 4:{
                            this.areaCoberturasAgregadas.setText(this.areaCoberturasAgregadas.getText()+"\nReembolso de hospedaje");
                            break;
                        }
                    }

                }

            }
        });

    }

    public void crearFrame() {
        setTitle("Nuevo seguro de viaje");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelSeguroViaje);
        setVisible(true);
    }

    public GestorSeguros getGestor(){
        return this.gestor;
    }


}
