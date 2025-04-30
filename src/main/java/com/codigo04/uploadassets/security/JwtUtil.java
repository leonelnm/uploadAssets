package com.codigo04.uploadassets.security;

import com.codigo04.uploadassets.config.JwtAssetsProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtAssetsProperties jwtAssetsProperties;

    public JwtUtil(JwtAssetsProperties jwtAssetsProperties) {
        this.jwtAssetsProperties = jwtAssetsProperties;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtAssetsProperties.getExpiration());

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(this.getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(this.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtAssetsProperties.getSecret().getBytes());
    }

}
