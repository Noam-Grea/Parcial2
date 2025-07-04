/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private MedicoDAO medicoDAO = new MedicoDAO();
    private PacienteDAO pacienteDAO = new PacienteDAO();

    public void guardarConsulta(Consulta consulta) {
        String sql = "INSERT INTO consulta (fecha, sintomas, diagnostico, tratamiento, id_medico, id_paciente) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(consulta.getFecha()));
            ps.setString(2, consulta.getSintomas());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamiento());
            ps.setString(5, consulta.getMedico().getId());
            ps.setString(6, consulta.getPaciente().getId());

            ps.executeUpdate();
            System.out.println("Consulta guardada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al guardar consulta: " + e.getMessage());
        }
    }

    public List<Consulta> obtenerConsultasPorPaciente(String idPaciente) {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE id_paciente = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idPaciente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medico medico = medicoDAO.buscarPorId(rs.getString("id_medico"));
                Paciente paciente = pacienteDAO.buscarPorId(rs.getString("id_paciente"));

                Consulta consulta = new Consulta(
                    rs.getInt("id"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("sintomas"),
                    rs.getString("diagnostico"),
                    rs.getString("tratamiento"),
                    medico,
                    paciente
                );

                lista.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener consultas: " + e.getMessage());
        }

        return lista;
    }

    public List<Consulta> obtenerConsultasPorMedico(String idMedico) {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE id_medico = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medico medico = medicoDAO.buscarPorId(rs.getString("id_medico"));
                Paciente paciente = pacienteDAO.buscarPorId(rs.getString("id_paciente"));

                Consulta consulta = new Consulta(
                    rs.getInt("id"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("sintomas"),
                    rs.getString("diagnostico"),
                    rs.getString("tratamiento"),
                    medico,
                    paciente
                );

                lista.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener consultas: " + e.getMessage());
        }

        return lista;
    }
}