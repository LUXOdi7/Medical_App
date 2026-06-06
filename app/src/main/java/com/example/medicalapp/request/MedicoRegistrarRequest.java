package com.example.medicalapp.request;

import com.google.gson.annotations.SerializedName;

public class MedicoRegistrarRequest {
    private String email;
    private String password;
    private String nombres;
    private String apellidos;
    private String dni;
    private String cmp;
    private String telefono;
    private String consultorio;

    @SerializedName("especialidad_id")
    private int especialidadId;

    public MedicoRegistrarRequest(String email, String password, String nombres, String apellidos, String dni, String cmp, String telefono, String consultorio, int especialidadId) {
        this.email = email;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cmp = cmp;
        this.telefono = telefono;
        this.consultorio = consultorio;
        this.especialidadId = especialidadId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }
}
