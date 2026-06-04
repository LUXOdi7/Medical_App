package com.example.medicalapp.response;

import com.example.medicalapp.data.Especialidad;

public class EspecialidadRegistrarResponse {
    private int especialidad_id;
    private String message;
    private Boolean status;

    public int getEspecialidad_id() {
        return especialidad_id;
    }

    public void setEspecialidad_id(int especialidad_id) {
        this.especialidad_id = especialidad_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
