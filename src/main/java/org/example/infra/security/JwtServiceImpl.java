package org.example.infra.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.application.JwtService;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtServiceImpl implements JwtService {

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(
                    "my-super-secret-key-my-super-secret-key".getBytes()
            );

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 heure

    @Override
    public String generateToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    @Override
    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}