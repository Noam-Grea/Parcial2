/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private ClinicaViewModel viewModel = new ClinicaViewModel();

    public VentanaPrincipal() {
        setTitle("Sistema de Consultas Médicas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JTabbedPane TPane = new JTabbedPane();

        TPane.addTab("Registrar", new PanelRegistro(viewModel));
        TPane.addTab("Nueva Consulta", new PanelConsulta(viewModel));
        TPane.addTab("Historial", new PanelHistorial(viewModel));

        add(TPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
