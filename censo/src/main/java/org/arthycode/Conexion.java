package org.arthycode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Creamos la conexion con la base de datos
    String url = "jdbc:mysql://localhost:3306/censo_app";
    String username = "root";
    String password = "";
    public Connection getConnection() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Conexión fallida." + e.getMessage());
        }
        return connection;
    }
}
