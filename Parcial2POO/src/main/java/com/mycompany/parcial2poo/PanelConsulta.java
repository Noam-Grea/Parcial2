/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class PanelConsulta extends JPanel {

    private ClinicaViewModel viewModel;

    private JComboBox<Medico> medicoCombo;
    private JComboBox<Paciente> pacienteCombo;
    private JTextArea sintomasArea, diagnosticoArea, tratamientoArea;
    private JButton botonGuardar;

    public PanelConsulta(ClinicaViewModel viewModel) {
        this.viewModel = viewModel;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registro de Consulta Médica", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        medicoCombo = new JComboBox<>();
        pacienteCombo = new JComboBox<>();
        sintomasArea = new JTextArea(2, 20);
        diagnosticoArea = new JTextArea(2, 20);
        tratamientoArea = new JTextArea(2, 20);
        botonGuardar = new JButton("Guardar Consulta");

        form.add(new JLabel("Médico:"));
        form.add(medicoCombo);
        form.add(new JLabel("Paciente:"));
        form.add(pacienteCombo);
        form.add(new JLabel("Síntomas:"));
        form.add(new JScrollPane(sintomasArea));
        form.add(new JLabel("Diagnóstico:"));
        form.add(new JScrollPane(diagnosticoArea));
        form.add(new JLabel("Tratamiento:"));
        form.add(new JScrollPane(tratamientoArea));
        form.add(new JLabel());
        form.add(botonGuardar);

        add(form, BorderLayout.CENTER);

        botonGuardar.addActionListener(this::guardarConsulta);

        cargarMedicos();
        cargarPacientes();
    }

    private void cargarMedicos() {
        medicoCombo.removeAllItems();
        List<Medico> medicos = viewModel.obtenerMedicos();
        for (Medico m : medicos) {
            medicoCombo.addItem(m);
        }
    }

    private void cargarPacientes() {
        pacienteCombo.removeAllItems();
        List<Paciente> pacientes = viewModel.obtenerPacientes();
        for (Paciente p : pacientes) {
            pacienteCombo.addItem(p);
        }
    }

    private void guardarConsulta(ActionEvent e) {
        Medico medico = (Medico) medicoCombo.getSelectedItem();
        Paciente paciente = (Paciente) pacienteCombo.getSelectedItem();
        String sintomas = sintomasArea.getText().trim();
        String diagnostico = diagnosticoArea.getText().trim();
        String tratamiento = tratamientoArea.getText().trim();

        if (medico == null || paciente == null || sintomas.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        viewModel.guardarConsulta(LocalDate.now(), sintomas, diagnostico, tratamiento, medico, paciente);
        JOptionPane.showMessageDialog(this, "✅ Consulta guardada correctamente");

        sintomasArea.setText("");
        diagnosticoArea.setText("");
        tratamientoArea.setText("");
    }
}