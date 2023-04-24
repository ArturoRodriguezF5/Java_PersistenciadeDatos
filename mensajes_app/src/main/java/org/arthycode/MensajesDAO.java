package org.arthycode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensajesDAO {

    public static  void crearMensajeDB(Mensajes mensajes) {

        Conexion db_connect = new Conexion();

        try (Connection connection = db_connect.getConnection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?, ?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, mensajes.getMensaje());
                ps.setString(2, mensajes.getAutor_mensaje());
                ps.executeUpdate();
                System.out.println("Mensaje creado.");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void leerMensajesDB() {
        Conexion db_conexion = new Conexion();

        try(Connection connection = db_conexion.getConnection()) {
            PreparedStatement ps = null;

            ResultSet rs = null;
            try {
                String query = "SELECT * FROM mensajes";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println("Id: " + rs.getString("id_mensaje"));
                    System.out.println("Mensaje: " + rs.getString("mensaje"));
                    System.out.println("Autor: " + rs.getString("autor_mensaje"));
                    System.out.println("Fecha de creación: " + rs.getString("fecha_mensaje"));
                }
                connection.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void borrarMensajes() {
    }

    public static void actualizarMensajesDB(Mensajes mensajes) {
    }
}
