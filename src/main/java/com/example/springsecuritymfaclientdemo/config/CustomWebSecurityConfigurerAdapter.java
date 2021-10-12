package com.example.springsecuritymfaclientdemo.config;

import com.example.springsecuritymfaclientdemo.auth.filter.CustomInitialAuthenticationFilter;
import com.example.springsecuritymfaclientdemo.auth.filter.JwtAuthenticationFilter;
import com.example.springsecuritymfaclientdemo.auth.provider.UsernameOtpAuthenticationProvider;
import com.example.springsecuritymfaclientdemo.auth.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private UsernameOtpAuthenticationProvider usernameOtpAuthenticationProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private CustomInitialAuthenticationFilter customInitialAuthenticationFilter;

    @Autowired
    public void setJwtAuthenticationFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Autowired
    public void setCustomInitialAuthenticationFilter(CustomInitialAuthenticationFilter customInitialAuthenticationFilter) {
        this.customInitialAuthenticationFilter = customInitialAuthenticationFilter;
    }

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
        /**
         * Adding Authentication Filters (customInitialAuthenticationFilter, jwtAuthenticationFilter) at BasicAuthenticationFilter
         */
        http.addFilterAt(customInitialAuthenticationFilter, BasicAuthenticationFilter.class).
                addFilterAt(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernameOtpAuthenticationProvider).
                authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
