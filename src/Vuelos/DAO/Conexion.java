package Vuelos.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlserver://DESKTOP-FC3II8O;databaseName=ReservaVuelos;instance=SQLEXPRESS;encrypt=false;trustServerCertificate=true;";
    private static final String USUARIO = "sa";
    private static final String CONTRASENA = "P@ssw0rd";

    public static Connection conexion = null;

    public static Connection obtenerConexion() {

        try {
            // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Obtener la conexión a la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC de SQL Server: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
