package org.arthycode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BdCRUD implements CalleableCRUD{


    @Override
    public void insertarRegistro(Persona persona) {
        Conexion conexion = new Conexion();

        try(Connection conn = conexion.getConnection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO censo_nueva (id_censo, NOMBRE, FECREG, DIR, TFNO) VALUES (?, ?, current_timestamp(), ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, persona.getDni());
                ps.setString(2, persona.getNombre());
                ps.setString(3, persona.getDireccion());
                ps.setInt(4, persona.getTelefono());
                ps.executeUpdate();
                System.out.println("Registro creado correctamente.");
                ps.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e){
            System.out.println("No se pudo agregar registro.." + e.getMessage());
        }
    }

    @Override
    public void verRegistros() {
        Conexion conexion = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conn = conexion.getConnection()) {
                String query = "SELECT * FROM censo_nueva";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println();
                    System.out.print("| DNI: " + rs.getInt("id_censo"));
                    System.out.print("  | Nombre: " + rs.getString("NOMBRE"));
                    System.out.print("  | Fecha: " + rs.getString("FECREG"));
                    System.out.print("  | Dirección: " + rs.getString("DIR"));
                    System.out.print("  | TFNO: " + rs.getInt("TFNO"));
                    System.out.println("\n");
                }

        } catch (SQLException e) {
            System.out.println("No se pudo listar los registros." + e.getMessage());
        }

    }

    @Override
    public void eliminarRegistro(int dni) {
        Conexion conexion = new Conexion();

        try(Connection conn = conexion.getConnection()) {
            PreparedStatement ps = null;
           try {
               String query = "DELETE FROM censo_nueva WHERE id_censo = ?";
               ps = conn.prepareStatement(query);
               ps.setInt(1, dni);
               ps.executeUpdate();
               System.out.println("Registro eliminado con éxito.");
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el registro." + e.getMessage());
        }
    }

    @Override
    public void editarRegistro(Persona persona) {
        Conexion conexion = new Conexion();

        try(Connection conn =  conexion.getConnection()) {
            PreparedStatement ps = null;

            try {
                String query = "UPDATE censo_nueva SET NOMBRE = ?, DIR = ?, TFNO = ? WHERE id_censo = ?";
                ps = conn.prepareStatement(query);

                ps.setString(1, persona.getNombre());
                ps.setString(2, persona.getDireccion());
                ps.setInt(3, persona.getTelefono());
                ps.setInt(4, persona.getDni());
                ps.executeUpdate();
                System.out.println("El registro se actualizó correctamamente");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("No fué posible editar el registro." + e.getMessage());
        }
    }
}

