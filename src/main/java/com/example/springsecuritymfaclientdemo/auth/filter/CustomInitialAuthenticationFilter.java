package com.example.springsecuritymfaclientdemo.auth.filter;

import com.example.springsecuritymfaclientdemo.auth.authentication.CustomUsernamePasswordAuthentication;
import com.example.springsecuritymfaclientdemo.auth.authentication.UsernameOtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomInitialAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("username");
        String code = request.getHeader("code");

        if(code == null){
            Authentication authentication = authenticationManager.authenticate(new CustomUsernamePasswordAuthentication(username, password));
            if(authentication.isAuthenticated()){
                filterChain.doFilter(request, response);
            }
        }else{
            Authentication authentication = new UsernameOtpAuthentication(username, code);
            Authentication authen = authenticationManager.authenticate(authentication);
            if(authen.isAuthenticated()){

            }
        }
    }
}
