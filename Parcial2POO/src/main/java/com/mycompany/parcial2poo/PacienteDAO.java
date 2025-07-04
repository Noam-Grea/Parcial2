/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void guardarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (id, nombre) VALUES (?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getId());
            ps.setString(2, paciente.getNombre());

            ps.executeUpdate();
            System.out.println("Paciente guardado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al guardar paciente: " + e.getMessage());
        }
    }

    public Paciente buscarPorId(String id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Paciente(
                    rs.getString("id"),
                    rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar paciente: " + e.getMessage());
        }

        return null;
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getString("id"),
                    rs.getString("nombre")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pacientes: " + e.getMessage());
        }

        return lista;
    }
}
