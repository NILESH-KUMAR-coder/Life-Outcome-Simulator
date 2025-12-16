package com.life.simulator.security;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.life.simulator.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final JwtConfig config;

    public JwtTokenProvider(JwtConfig config) {
        this.config = config;
        this.key = Keys.hmacShaKeyFor(config.getSecret().getBytes());
    }

    // Updated to include role
    public String generateToken(Long userId, String email, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + config.getExpirationMs());

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .claim("role", role) // Add role to claims
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public Long getUserId(String token) {
        return Long.parseLong(validateToken(token).getBody().getSubject());
    }

    // Extract the role from the token
    public String getRole(String token) {
        return validateToken(token).getBody().get("role", String.class);
    }
}
