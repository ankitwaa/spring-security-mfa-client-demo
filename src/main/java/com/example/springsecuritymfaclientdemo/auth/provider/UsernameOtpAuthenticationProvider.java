package com.example.springsecuritymfaclientdemo.auth.provider;

import com.example.springsecuritymfaclientdemo.auth.authentication.CustomUsernamePasswordAuthentication;
import com.example.springsecuritymfaclientdemo.auth.authentication.UsernameOtpAuthentication;
import com.example.springsecuritymfaclientdemo.auth.provider.proxy.RemoteAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialException;

@Component
public class UsernameOtpAuthenticationProvider implements AuthenticationProvider {

    private RemoteAuthenticationProvider remoteAuthenticationProvider;

    @Autowired
    public void setRemoteAuthenticationProvider(RemoteAuthenticationProvider remoteAuthenticationProvider) {
        this.remoteAuthenticationProvider = remoteAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        boolean authenticate = false;
        try {
            authenticate = remoteAuthenticationProvider.authenticateOtp(username, password);
            if(authenticate){
                authentication.setAuthenticated(true);
            }else{
                throw new BadCredentialsException("User is not valid!");
            }
        } catch (CredentialException e) {
            throw new BadCredentialsException("User is not valid!");
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernameOtpAuthentication.class);
    }
}