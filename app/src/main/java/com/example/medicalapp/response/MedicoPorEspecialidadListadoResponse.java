package com.example.medicalapp.response;

import com.example.medicalapp.data.Medico;

public class MedicoPorEspecialidadListadoResponse {
    private Medico[] data;
    private String message;
    private boolean status;

    public Medico[] getData() {
        return data;
    }

    public void setData(Medico[] data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
