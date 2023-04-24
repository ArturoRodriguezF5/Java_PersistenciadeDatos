package org.arthycode;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    String url = "jdbc:mysql://localhost:3306/mensajes_app";
    String username = "root";
    String password = "";
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            System.out.println("Conexión fallida con el servidor. " + e.getMessage());
        }
       return connection;
    }
}
