package com.example.companyforum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this as a configuration class
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfig {

    // Bean definition for the PasswordEncoder, which we'll use to hash passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean definition for the main security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Allow access to the homepage, registration page, and static assets (CSS, JS) for everyone
                        .requestMatchers("/", "/register", "/css/**").permitAll()
                        // All other requests must be authenticated (user must be logged in)
                        .anyRequest().authenticated()
                )
                // Configure the login form
                .formLogin(form -> form
                        .loginPage("/login") // Specify the custom login page URL
                        .defaultSuccessUrl("/", true) // Redirect to homepage on successful login
                        .permitAll() // Allow everyone to access the login page
                )
                // Configure logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                        .permitAll()
                );

        return http.build();
    }
}