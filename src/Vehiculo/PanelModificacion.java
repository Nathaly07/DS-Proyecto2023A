package Vehiculo;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class PanelModificacion {
    public JPanel panelModificarReserva;
    private JTextField txtCiudadEntrega;
    private JTextField txtFechaEntrega;
    private JTextField txtCiudadRetorno;
    private JTextField txtFechaRetorno;
    private JTable table1;
    private JButton modificarReservaButton;
    private ReservaVehiculo reservaVehiculo;

    public PanelModificacion(ReservaVehiculo reservaVehiculo) {
        this.reservaVehiculo = reservaVehiculo;
        txtCiudadEntrega.setText(reservaVehiculo.getCiudadDeEntrega());
        txtCiudadRetorno.setText(reservaVehiculo.getCiudadRetorno());

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaEntrega1 = formato.format(reservaVehiculo.getFechaInicio());
        String fechaEntrega2 = formato.format(reservaVehiculo.getFechaRetorno());
        txtFechaEntrega.setText(fechaEntrega1);
        txtFechaRetorno.setText(fechaEntrega2);

        modificarReservaButton.addActionListener(e -> {
            reservaVehiculo.modificarReserva(txtCiudadEntrega.getText(),txtCiudadRetorno.getText(),txtFechaEntrega.getText(),txtFechaRetorno.getText()); //TODO: MATIAS
        });
    }


}
