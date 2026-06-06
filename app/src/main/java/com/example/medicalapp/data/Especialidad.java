package com.example.medicalapp.data;

import com.google.gson.annotations.SerializedName;

public class Especialidad {
    private int id;
    private String nombre;
    private String descripcion;
    @SerializedName("imagen_url")
    private String imagenUrl;

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return nombre; // Esto permite que el ArrayAdapter muestre el nombre en el dropdown
    }
}
