package Principal;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class InterfazDatosComunes extends JFrame{
    private JPanel pnlDatosComunes;
    private JComboBox cmbDestinoComun;
    private JPanel pnlFechaComun;
    private JButton btnConfirmarDatos;
    private JDateChooser fechaComun = new JDateChooser();

    public InterfazDatosComunes(Sesion sesion) {
        pnlFechaComun.add(fechaComun);

        btnConfirmarDatos.addActionListener(e -> {
            if(fechaComun.getDate() != null && cmbDestinoComun.getSelectedIndex() != 0) {
                sesion.setFechaComun(fechaComun.getDate());
                sesion.setDestinoComun(cmbDestinoComun.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Ahora, podrás hacer tu plan con estos datos.", "Muchas gracias", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void crearFrame() {
        setTitle("Datos comunes");
        setSize(500, 180);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pnlDatosComunes);
        setVisible(true);
    }
}
