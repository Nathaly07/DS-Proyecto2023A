package Hospedaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloHospedaje extends JFrame {

    private JPanel mainPanel;
    private JButton crearReservaButton;
    private JButton confirmarReservaButton;
    private JButton cambiarReservaButton;
    private JTextArea textArea1;
    private JButton cancelarReservaButton;
    private JButton buscarHabitacionesDisponiblesButton;
    private JButton pagarButton;

    public ModuloHospedaje(){
        crearFrame();

        crearReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        confirmarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui se debe cre
            }
        });
        cambiarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarHabitacionesDisponiblesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void crearFrame(){
        setContentPane(mainPanel);
        setVisible(true);
        this.setLocationRelativeTo(null);
        setTitle("Modulo hospedaje");
        setSize(400,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}