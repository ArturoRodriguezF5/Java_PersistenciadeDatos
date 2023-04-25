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
                    System.out.println("Id: " + rs.getInt("id_mensaje"));
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

    public static void borrarMensajes(int id_mensaje) {
        Conexion conexion = new Conexion();

        try(Connection conn = conexion.getConnection()) {
            PreparedStatement ps = null;
            try {
                String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id_mensaje);
                ps.executeUpdate();
                System.out.println("El mensaje fue borrado exitosamente.");
                conn.close();

            } catch (SQLException ex) {
                System.out.println("No se pudo borrar el mensaje..." + ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void actualizarMensajesDB(Mensajes mensajes) {
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.getConnection()) {
            PreparedStatement ps = null;
            try {
                String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensaje = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, mensajes.getMensaje());
                ps.setInt(2, mensajes.getId_mensaje());
                ps.executeUpdate();
                System.out.println("Mensaje se actualizó correctamente..");

            } catch (SQLException ex) {
                System.out.println("No se pudo editar el mensaje." + ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error no se tiene acceso a la BD." + e.getMessage());
        }
    }
}
