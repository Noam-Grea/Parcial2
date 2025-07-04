/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginFrame() {
        setTitle("Login - Clínica");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Iniciar Sesión", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        idField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Entrar");

        form.add(new JLabel("Usuario ID:"));
        form.add(idField);
        form.add(new JLabel("Contraseña:"));
        form.add(passwordField);
        form.add(new JLabel());
        form.add(loginButton);

        add(form, BorderLayout.CENTER);

        loginButton.addActionListener(this::login);
    }

    private void login(ActionEvent e) {
        String id = idField.getText().trim();
        String contrasena = new String(passwordField.getPassword());

        if (usuarioDAO.autenticar(id, contrasena)) {
            JOptionPane.showMessageDialog(this, "Bienvenido");

            // Lancer l'application principale
            dispose(); // Ferme la fenêtre de login
            SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}