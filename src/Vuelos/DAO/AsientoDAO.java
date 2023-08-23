package Vuelos.DAO;

import Vuelos.Logica.Asiento;
import Vuelos.Logica.Vuelo;

import javax.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsientoDAO {
    private Conexion con;

    public void ActualizarEstado(int vuelo, int asiento, int estado) {
        Connection c = con.obtenerConexion();
        String sql = "Update Asientos set  estado= ? where num_asiento = ? and cod_vuelo = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, asiento);
            ps.setInt(2, vuelo);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro actualizado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error en la base de Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Asiento> BuscarAsientos(int cod_vuelo) {
        List<Asiento> asientos = new ArrayList<>();
        Connection c = con.obtenerConexion();
        String sql = "Select * from Asientos where cod_vuelo = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, cod_vuelo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Asiento a = new Asiento(rs.getInt(1), rs.getInt(3), rs.getString(4),
                        rs.getDouble(5), rs.getString(6));
                asientos.add(a);
            }
            return asientos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


}
