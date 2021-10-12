package com.example.springsecuritymfaclientdemo.auth.provider.proxy.impl;

import com.example.springsecuritymfaclientdemo.auth.provider.proxy.RemoteAuthenticationProvider;
import com.example.springsecuritymfaclientdemo.auth.provider.proxy.dto.AuthenticationResult;
import com.example.springsecuritymfaclientdemo.auth.provider.proxy.dto.UsernameOtp;
import com.example.springsecuritymfaclientdemo.auth.provider.proxy.dto.UsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.CredentialException;

@Component
public class RemoteAuthenticationProviderProxyImpl implements RemoteAuthenticationProvider {

    @Value("${auth.server.baseurl}")
    private String authServerBaseUrl;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean authenticate(String username, String password) throws CredentialException {
        UsernamePassword usernamePassword= new UsernamePassword();
        usernamePassword.setPassword(password);
        usernamePassword.setUsername(username);
        ResponseEntity<AuthenticationResult> authenticationResult = restTemplate.postForEntity(authServerBaseUrl+"/user/authenticate", usernamePassword, AuthenticationResult.class);
        if(authenticationResult.getStatusCode() == HttpStatus.OK){
            return true;
        }
        return false;
    }

    @Override
    public boolean authenticateOtp(String username, String otp) throws CredentialException {
        UsernameOtp usernamePassword= new UsernameOtp();
        usernamePassword.setOtp(otp);
        usernamePassword.setUsername(username);
        ResponseEntity<AuthenticationResult> authenticationResult = restTemplate.postForEntity(authServerBaseUrl+"/user/token/validate", usernamePassword, AuthenticationResult.class);
        if(authenticationResult.getStatusCode() == HttpStatus.OK){
            return true;
        }
        return false;
    }
}
