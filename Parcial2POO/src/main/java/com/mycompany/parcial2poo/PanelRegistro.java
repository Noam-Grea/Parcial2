/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelRegistro extends JPanel {
    private ClinicaViewModel viewModel;
    private JComboBox<String> tipoCombo;
    private JTextField idField, nombreField, especialidadField;
    private JButton botonGuardar;

    public PanelRegistro(ClinicaViewModel viewModel) {
        this.viewModel = viewModel;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registro de Médicos y Pacientes", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        tipoCombo = new JComboBox<>(new String[]{"Médico", "Paciente"});
        idField = new JTextField();
        nombreField = new JTextField();
        especialidadField = new JTextField();

        form.add(new JLabel("Tipo:"));
        form.add(tipoCombo);
        form.add(new JLabel("ID:"));
        form.add(idField);
        form.add(new JLabel("Nombre:"));
        form.add(nombreField);
        form.add(new JLabel("Especialidad:"));
        form.add(especialidadField);
        botonGuardar = new JButton("Guardar");
        form.add(new JLabel());
        form.add(botonGuardar);

        add(form, BorderLayout.CENTER);

        tipoCombo.addActionListener(e -> toggleEspecialidad());
        botonGuardar.addActionListener(this::guardarPersona);

        toggleEspecialidad();
    }

    private void toggleEspecialidad() {
        boolean esMedico = tipoCombo.getSelectedItem().equals("Médico");
        especialidadField.setEnabled(esMedico);
        especialidadField.setText(esMedico ? "" : "-");
    }

    private void guardarPersona(ActionEvent e) {
    String id = idField.getText().trim();
    String nombre = nombreField.getText().trim();
    String tipo = (String) tipoCombo.getSelectedItem();

    try {
        if (tipo.equals("Médico")) {
            String esp = especialidadField.getText().trim();
            viewModel.guardarMedico(id, nombre, esp);
            JOptionPane.showMessageDialog(this, "✅ Médico guardado correctamente");

        } else {
            viewModel.guardarPaciente(id, nombre);
            JOptionPane.showMessageDialog(this, "✅ Paciente guardado correctamente");
        }

      
        idField.setText("");
        nombreField.setText("");
        especialidadField.setText("");

    } catch (CamposVaciosException | YaExisteException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}