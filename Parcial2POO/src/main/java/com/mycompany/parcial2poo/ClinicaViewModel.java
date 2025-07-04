/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import java.time.LocalDate;
import java.util.List;

public class ClinicaViewModel {

    private MedicoDAO medicoDAO = new MedicoDAO();
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    // Médico
    
    public void guardarMedico(String id, String nombre, String especialidad)
        throws CamposVaciosException, YaExisteException {
    
    if (id.isEmpty() || nombre.isEmpty() || especialidad.isEmpty()) {
        throw new CamposVaciosException("Todos los campos son obligatorios.");
    }

    if (medicoDAO.buscarPorId(id) != null) {
        throw new YaExisteException("El médico con ese ID ya existe.");
    }

    Medico medico = new Medico(id, nombre, especialidad);
    medicoDAO.guardarMedico(medico);
}

    public List<Medico> obtenerMedicos() {
        return medicoDAO.listarTodos();
    }

    // Paciente
    public void guardarPaciente(String id, String nombre)
        throws CamposVaciosException, YaExisteException {
    
    if (id.isEmpty() || nombre.isEmpty()) {
        throw new CamposVaciosException("Todos los campos son obligatorios.");
    }

    if (pacienteDAO.buscarPorId(id) != null) {
        throw new YaExisteException("El paciente con ese ID ya existe.");
    }

    Paciente paciente = new Paciente(id, nombre);
    pacienteDAO.guardarPaciente(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteDAO.listarTodos();
    }

    // Consulta
    public void guardarConsulta(LocalDate fecha, String sintomas, String diagnostico,
                                String tratamiento, Medico medico, Paciente paciente) {
        Consulta consulta = new Consulta(fecha, sintomas, diagnostico, tratamiento, medico, paciente);
        consultaDAO.guardarConsulta(consulta);
    }

    public List<Consulta> obtenerConsultasPorPaciente(String idPaciente) {
        return consultaDAO.obtenerConsultasPorPaciente(idPaciente);
    }

    public List<Consulta> obtenerConsultasPorMedico(String idMedico) {
        return consultaDAO.obtenerConsultasPorMedico(idMedico);
    }
}