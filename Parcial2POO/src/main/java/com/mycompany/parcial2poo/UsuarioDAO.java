/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import java.sql.*;

public class UsuarioDAO {

    public Usuario buscarPorId(String id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
            }

        } catch (SQLException e) {
            System.out.println(" Error al buscar usuario: " + e.getMessage());
        }

        return null;
    }

    public boolean autenticar(String id, String contrasena) {
        Usuario usuario = buscarPorId(id);
        return usuario != null && usuario.getContrasena().equals(contrasena);
    }
}