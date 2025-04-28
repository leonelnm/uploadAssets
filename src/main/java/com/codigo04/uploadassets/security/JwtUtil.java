package com.codigo04.uploadassets.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

public class JwtUtil {

    private static final String JWT_SECRET = "mi_clave_secreta_mas_secreta_de_muuuuy_secreta";  // Cambia esta clave por una más segura
    private static final long JWT_EXPIRATION = 86400000; // 24h

    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes()))
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

}
