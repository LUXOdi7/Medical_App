package com.example.medicalapp.request;

import com.google.gson.annotations.SerializedName;

public class MedicoRegistrarRequest {
    @SerializedName("usuario_id")
    private int usuarioId;
    @SerializedName("nombres")
    private String nombres;
    @SerializedName("apellidos")
    private String apellidos;
    @SerializedName("dni")
    private String dni;
    @SerializedName("cmp")
    private String cmp;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("consultorio")
    private String consultorio;
    @SerializedName("estado_medico_id")
    private String estadoMedicoId;

    public MedicoRegistrarRequest() {
    }

    public MedicoRegistrarRequest(int usuarioId, String nombres, String apellidos, String dni, String cmp, String telefono, String consultorio, String estadoMedicoId) {
        this.usuarioId = usuarioId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cmp = cmp;
        this.telefono = telefono;
        this.consultorio = consultorio;
        this.estadoMedicoId = estadoMedicoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getEstadoMedicoId() {
        return estadoMedicoId;
    }

    public void setEstadoMedicoId(String estadoMedicoId) {
        this.estadoMedicoId = estadoMedicoId;
    }
}
