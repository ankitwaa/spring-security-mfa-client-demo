package com.example.springsecuritymfaclientdemo.config;

import com.example.springsecuritymfaclientdemo.auth.provider.UsernameOtpAuthenticationProvider;
import com.example.springsecuritymfaclientdemo.auth.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private UsernameOtpAuthenticationProvider usernameOtpAuthenticationProvider;

    @Qualifier("usernamePasswordAuthenticationProvider")
    @Autowired
    public void setUsernamePasswordAuthenticationProvider(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    }

    @Qualifier("usernameOtpAuthenticationProvider")
    @Autowired
    public void setUsernameOtpAuthenticationProvider(UsernameOtpAuthenticationProvider usernameOtpAuthenticationProvider) {
        this.usernameOtpAuthenticationProvider = usernameOtpAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernameOtpAuthenticationProvider).
                authenticationProvider(usernamePasswordAuthenticationProvider);
    }
}
