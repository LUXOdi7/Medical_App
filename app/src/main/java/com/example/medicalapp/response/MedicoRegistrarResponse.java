package com.example.medicalapp.response;

public class MedicoRegistrarResponse {
    private int medico_id;
    private String message;
    private Boolean status;

    public int getEspecialidad_id() {
        return medico_id;
    }

    public void setEspecialidad_id(int especialidad_id) {
        this.medico_id = especialidad_id;
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
