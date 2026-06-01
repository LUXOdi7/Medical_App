package com.example.medicalapp.model;

public class Medico {
    private String nombre;
    private String especialidad;
    private String horario;
    private String consultorio;
    private int imagen;

    public Medico(String nombre, String especialidad, String horario, String consultorio, int imagen) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horario = horario;
        this.consultorio = consultorio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getHorario() {
        return horario;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public int getImagen() {
        return imagen;
    }
}
