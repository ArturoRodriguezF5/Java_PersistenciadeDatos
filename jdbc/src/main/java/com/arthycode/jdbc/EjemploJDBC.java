package com.arthycode.jdbc;

import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mensajes_app?serverTimezone=UTC";
        String username = "root";
        String password = "";


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            System.out.println("Conexión establecida con éxito.");
            try {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM mensajes");
                System.out.println("Si se encontró");
                while (resultSet.next()) {
                    System.out.print("Id: " + resultSet.getString("id_mensaje"));
                    System.out.print(" | Mensaje: " + resultSet.getString("mensaje"));
                    System.out.print(" | Autor_Mensaje: " + resultSet.getString("autor_mensaje"));
                    System.out.print(" | Fecha del mensaje: " + resultSet.getString("fecha_mensaje"));
                }
                
                connection.close();
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error conexión fallida." + e.getMessage());
        }
    }
}
