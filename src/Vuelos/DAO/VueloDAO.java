package Vuelos.DAO;
import Vuelos.Logica.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloDAO {
    private Conexion c ;
    public List<Vuelo> ConsultarVuelo(String origen, String destino) {
        Connection con = c.obtenerConexion();
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "Select * from Vuelos where origen like '" + origen + "%' and destino like '" + destino + "%'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vuelo v = new Vuelo(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                        rs.getInt(8));
                vuelos.add(v);
            }
            return vuelos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    }

