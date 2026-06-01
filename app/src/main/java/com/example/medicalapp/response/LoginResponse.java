package com.example.medicalapp.response;

import com.example.medicalapp.data.Login;

import java.util.ArrayList;

public class LoginResponse {
    private Login data;
    private String message;
    private boolean status;

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
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
