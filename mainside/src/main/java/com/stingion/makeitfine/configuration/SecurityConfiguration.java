/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ConditionalOnProperty(value = "security.enable", havingValue = "true", matchIfMissing = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${security.enable:true}")
    private boolean securityEnable;

    @Value("${security.antPattern:/**}")
    private String antPattern;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) {
        if (securityEnable) {
            configureGlobalSecurityIfSecurityON(auth);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityEnable) {
            configureIfSecurityIsON(http);
        }
    }

    private void configureGlobalSecurityIfSecurityON(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    private void configureIfSecurityIsON(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login**", "/public/**", "/Access_Denied").permitAll();
        http.authorizeRequests()
                .antMatchers(antPattern != null ? antPattern : "/**")
                .access("hasRole('ADMIN') or hasRole('USER')")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("ssoId")
                .passwordParameter("password")
                .failureUrl("/Access_Denied")
                .and()
                .logout();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Access_Denied");
        http.headers().frameOptions().disable();
    }
}
