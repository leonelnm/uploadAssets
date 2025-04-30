package com.codigo04.uploadassets.service;

import com.codigo04.uploadassets.security.JwtUtil;
import com.codigo04.uploadassets.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public String login(String username, String password) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return generateToken(userDetails.getUsername());

    }

    private String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

}
