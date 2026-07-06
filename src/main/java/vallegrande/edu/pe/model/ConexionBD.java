package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL ="jdbc:mysql://db-hackaton.cikim32di9mn.us-east-1.rds.amazonaws.com:3306/basedatos";
    private static final String USER ="admin";
    private static final String PASSWORD ="huamaniespi";

    public static Connection getConexion(){

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa.");

        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());

        }
        return conexion;
    }
}
