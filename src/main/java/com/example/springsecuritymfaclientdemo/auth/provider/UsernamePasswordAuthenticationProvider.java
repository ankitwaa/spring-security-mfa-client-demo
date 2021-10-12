package com.example.springsecuritymfaclientdemo.auth.provider;

import com.example.springsecuritymfaclientdemo.auth.authentication.CustomUsernamePasswordAuthentication;
import com.example.springsecuritymfaclientdemo.auth.provider.proxy.RemoteAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialException;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

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
            authenticate = remoteAuthenticationProvider.authenticate(username, password);
            if(authenticate){
                return new CustomUsernamePasswordAuthentication(username, password);
            }else{
                throw new BadCredentialsException("User is not valid!");
            }
        } catch (CredentialException e) {
            throw new BadCredentialsException("User is not valid!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomUsernamePasswordAuthentication.class);
    }
}
