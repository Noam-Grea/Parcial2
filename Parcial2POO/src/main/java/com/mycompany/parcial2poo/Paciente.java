/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2poo;

public class Paciente extends Persona {

    public Paciente(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
