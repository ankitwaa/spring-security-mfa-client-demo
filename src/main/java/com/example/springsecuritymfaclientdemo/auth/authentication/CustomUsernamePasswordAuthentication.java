package com.example.springsecuritymfaclientdemo.auth.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

    public CustomUsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public CustomUsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

}
