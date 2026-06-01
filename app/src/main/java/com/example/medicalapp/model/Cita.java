package com.example.medicalapp.model;

public class Cita {

    private String paciente;
    private String dni;
    private String especialidad;
    private String medico;
    private String fecha;
    private String motivo;
    private String turno;
    private boolean pacienteOncologico;

    public Cita(String paciente, String dni, String especialidad, String medico,
                String fecha, String motivo, String turno, boolean pacienteOncologico) {
        this.paciente = paciente;
        this.dni = dni;
        this.especialidad = especialidad;
        this.medico = medico;
        this.fecha = fecha;
        this.motivo = motivo;
        this.turno = turno;
        this.pacienteOncologico = pacienteOncologico;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getDni() {
        return dni;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getTurno() {
        return turno;
    }

    public boolean isPacienteOncologico() {
        return pacienteOncologico;
    }

}
