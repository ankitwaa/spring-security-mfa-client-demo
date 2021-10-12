package com.example.springsecuritymfaclientdemo.auth.provider.proxy;

import javax.security.auth.login.CredentialException;

public interface RemoteAuthenticationProvider {
    boolean authenticate(String username, String password) throws CredentialException;
    boolean authenticateOtp(String username, String otp) throws CredentialException;
}
