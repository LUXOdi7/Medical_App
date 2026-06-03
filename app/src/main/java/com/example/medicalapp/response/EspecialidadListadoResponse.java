package com.example.medicalapp.response;

import com.example.medicalapp.data.Especialidad;

public class EspecialidadListadoResponse {
    private Especialidad[] data;
    private String message;
    private boolean status;

    public Especialidad[] getData() {
        return data;
    }

    public void setData(Especialidad[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
