/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

import java.time.LocalDate;

public class Consulta {
    private int id;
    private LocalDate fecha;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;

    private Medico medico;
    private Paciente paciente;

    public Consulta(int id, LocalDate fecha, String sintomas, String diagnostico,
                    String tratamiento, Medico medico, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medico = medico;
        this.paciente = paciente;
    }

   
    public Consulta(LocalDate fecha, String sintomas, String diagnostico,
                    String tratamiento, Medico medico, Paciente paciente) {
        this(0, fecha, sintomas, diagnostico, tratamiento, medico, paciente);
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return fecha + " - Dr. " + medico.getNombre() + " → " + diagnostico;
    }
}
