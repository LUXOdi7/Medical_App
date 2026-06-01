package com.example.medicalapp.data;

import com.google.gson.annotations.SerializedName;

public class Login {
    //Se declara lo que esta dentro de data (RESPONSE)
    private String email;

    @SerializedName("estado_usuario")
    private String estadoUsuario;
    private String nombre;
    private String rol;
    private String token;
    @SerializedName("usuario_id")
    private int usuarioId;

    //Atributo que permite almacenar los datos de la sesión
    public static Login DATOS_SESION;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
