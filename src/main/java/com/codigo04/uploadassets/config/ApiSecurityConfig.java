package com.codigo04.uploadassets.config;

import com.codigo04.uploadassets.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class ApiSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public ApiSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/login", "/api/auth/login").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(
                                (request, response, authException) -> {
                                    if(request.getRequestURI().startsWith("/api/")){
                                        response.sendError(HttpStatus.UNAUTHORIZED.value());
                                    }else{
                                        response.sendRedirect("/login");
                                    }
                                }))
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/assets", true)
                        .permitAll())
                .userDetailsService(userDetailsService)
//                .addFilterBefore(
//                        new JwtAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
                .build();


    }


}
