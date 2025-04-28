package com.codigo04.uploadassets.service;

import com.codigo04.uploadassets.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;

    public String login(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authenticationToken);

        return generateToken(username);
    }

    private String generateToken(String username) {
        JwtUtil jwtUtil = new JwtUtil();
        return jwtUtil.generateToken(username);
    }

}
