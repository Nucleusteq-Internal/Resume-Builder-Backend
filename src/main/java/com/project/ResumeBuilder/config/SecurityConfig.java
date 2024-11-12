package com.project.ResumeBuilder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer -> customizer.disable())
                .cors(Customizer.withDefaults()) // Enable CORS
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/users/login", "/users/register").permitAll()
                        .requestMatchers("/users/forgot-password", "/users/reset-password").permitAll()
                        .requestMatchers("/api/user-profiles/getAllProfile").hasAuthority("ROLE_HR")
                        .requestMatchers("/users/employees").hasAuthority("ROLE_HR")
                        .requestMatchers("/api/user-profiles/{id}/delete").hasAnyAuthority("ROLE_HR", "ROLE_EMPLOYEE")
                        .requestMatchers("/api/user-profiles/create").hasAnyAuthority("ROLE_HR", "ROLE_EMPLOYEE")
                        .requestMatchers("/api/user-profiles/update/{id}").hasAnyAuthority("ROLE_HR", "ROLE_EMPLOYEE")
                        .requestMatchers("/api/user-profiles/{id}").hasAnyAuthority("ROLE_HR", "ROLE_EMPLOYEE")
                        .requestMatchers("/api/user-profiles/user/{userId}").hasAnyAuthority("ROLE_HR", "ROLE_EMPLOYEE")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);


        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}

