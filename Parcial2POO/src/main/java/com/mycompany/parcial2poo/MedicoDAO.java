/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void guardarMedico(Medico medico) {
        String sql = "INSERT INTO medico (id, nombre, especialidad) VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medico.getId());
            ps.setString(2, medico.getNombre());
            ps.setString(3, medico.getEspecialidad());

            ps.executeUpdate();
            System.out.println("Médico guardado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al guardar médico: " + e.getMessage());
        }
    }

    public Medico buscarPorId(String id) {
        String sql = "SELECT * FROM medico WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Medico(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar médico: " + e.getMessage());
        }

        return null;
    }

    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medico m = new Medico(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar médicos: " + e.getMessage());
        }

        return lista;
    }
}