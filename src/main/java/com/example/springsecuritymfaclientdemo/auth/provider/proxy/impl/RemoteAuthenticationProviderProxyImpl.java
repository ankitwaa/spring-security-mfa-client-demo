package com.example.springsecuritymfaclientdemo.auth.provider.proxy.impl;

import com.example.springsecuritymfaclientdemo.auth.provider.proxy.RemoteAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;

import javax.security.auth.login.CredentialException;

public class RemoteAuthenticationProviderProxyImpl implements RemoteAuthenticationProvider {

    @Value("$auth.server.baseurl")
    private String authServerBaseUrl;

    @Override
    public boolean authenticate(String username, String password) throws CredentialException {
        return false;
    }

    @Override
    public boolean authenticateOtp(String username, String otp) throws CredentialException {
        return false;
    }
}
