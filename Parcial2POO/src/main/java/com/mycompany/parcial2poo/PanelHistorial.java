/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelHistorial extends JPanel {

    private ClinicaViewModel viewModel;

    private JComboBox<String> tipoCombo;
    private JComboBox<Object> selectorCombo;
    private JTextArea historialArea;
    private JButton botonBuscar;

    public PanelHistorial(ClinicaViewModel viewModel) {
        this.viewModel = viewModel;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Historial de Consultas", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        tipoCombo = new JComboBox<>(new String[]{"Paciente", "Médico"});
        selectorCombo = new JComboBox<>();
        botonBuscar = new JButton("Buscar");

        form.add(new JLabel("Tipo:"));
        form.add(tipoCombo);
        form.add(new JLabel("Seleccione:"));
        form.add(selectorCombo);
        form.add(new JLabel());
        form.add(botonBuscar);

        add(form, BorderLayout.NORTH);

        historialArea = new JTextArea(15, 40);
        historialArea.setEditable(false);
        add(new JScrollPane(historialArea), BorderLayout.CENTER);

        tipoCombo.addActionListener(e -> cargarOpciones());
        botonBuscar.addActionListener(this::mostrarHistorial);

        cargarOpciones();
    }

    private void cargarOpciones() {
        selectorCombo.removeAllItems();
        String tipo = (String) tipoCombo.getSelectedItem();

        if (tipo.equals("Paciente")) {
            List<Paciente> pacientes = viewModel.obtenerPacientes();
            for (Paciente p : pacientes) selectorCombo.addItem(p);
        } else {
            List<Medico> medicos = viewModel.obtenerMedicos();
            for (Medico m : medicos) selectorCombo.addItem(m);
        }
    }

    private void mostrarHistorial(ActionEvent e) {
        historialArea.setText("");
        String tipo = (String) tipoCombo.getSelectedItem();
        Object seleccionado = selectorCombo.getSelectedItem();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un elemento", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Consulta> consultas;

        if (tipo.equals("Paciente")) {
            Paciente p = (Paciente) seleccionado;
            consultas = viewModel.obtenerConsultasPorPaciente(p.getId());
        } else {
            Medico m = (Medico) seleccionado;
            consultas = viewModel.obtenerConsultasPorMedico(m.getId());
        }

        if (consultas.isEmpty()) {
            historialArea.setText("No hay consultas registradas.");
        } else {
            for (Consulta c : consultas) {
                historialArea.append("Fecha: " + c.getFecha() + "\n");
                historialArea.append(" Médico: " + c.getMedico().getNombre() + "\n");
                historialArea.append("Paciente: " + c.getPaciente().getNombre() + "\n");
                historialArea.append("Síntomas: " + c.getSintomas() + "\n");
                historialArea.append("Diagnóstico: " + c.getDiagnostico() + "\n");
                historialArea.append("Tratamiento: " + c.getTratamiento() + "\n");
                historialArea.append("\n-----------------------------------------\n");
            }
        }
    }
}