package com.example.springsecuritymfaclientdemo.auth.provider.proxy.dto;

public class AuthenticationResult {

    private String errorMessage;
    private String successMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
