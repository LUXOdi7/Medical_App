package com.example.medicalapp.data;

import com.google.gson.annotations.SerializedName;

public class Medico {
    private int id;
    @SerializedName("usuario_id")
    private int usuarioId;
    @SerializedName("imagen_url")
    private String imagenUrl;
    private String medico;
    private String apellidos;
    private String dni;
    private String cmp;
    private String telefono;
    private String consultorio;
    @SerializedName("estado_medico_id")
    private int estadoMedicoId;

    @SerializedName("especialidad_id")
    private int especialidadId;

    @SerializedName("especialidad")
    private String especialidadNombre;

    public int getId() {
        return id;
    }


    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getmedico() {
        return medico;
    }

    public void setmedico(String nombres) {
        this.medico = nombres;
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

    public int getEstadoMedicoId() {
        return estadoMedicoId;
    }

    public void setEstadoMedicoId(int estadoMedicoId) {
        this.estadoMedicoId = estadoMedicoId;
    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getEspecialidadNombre() {
        return especialidadNombre;
    }

    public void setEspecialidadNombre(String especialidadNombre) {
        this.especialidadNombre = especialidadNombre;
    }
}
