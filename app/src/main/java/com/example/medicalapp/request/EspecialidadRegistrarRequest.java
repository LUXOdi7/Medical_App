package com.example.medicalapp.request;

import com.google.gson.annotations.SerializedName;

public class EspecialidadRegistrarRequest {

    @SerializedName("usuario_id")
    private int usuarioId;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;

    public EspecialidadRegistrarRequest() {
    }

    public EspecialidadRegistrarRequest(int usuarioId, String nombre, String descripcion) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
