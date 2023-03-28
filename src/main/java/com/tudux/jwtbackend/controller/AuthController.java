package com.tudux.jwtbackend.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
public class AuthController {
    private static final SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    private static final int TOKEN_EXPIRATION_TIME = 30; // 30 minutes

    @PostMapping("/api/login")
    public JwtResponse login(@RequestBody LoginRequest request) throws AuthenticationException {
        // Your authentication logic goes here
        boolean isAuthenticated = authenticate(request.getUsername(), request.getPassword());
        if (isAuthenticated) {
            // Generate JWT token
            String token = generateJwtToken(request.getUsername());
            return new JwtResponse(token);
        } else {
            throw new AuthenticationException("Invalid credentials");
        }
    }

    private boolean authenticate(String username, String password) {
        // Your authentication logic goes here
        return true;
    }

    private String generateJwtToken(String username) {
        Instant now = Instant.now();
        Instant expirationTime = now.plus(TOKEN_EXPIRATION_TIME, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationTime))
                .signWith(secretKey)
                .compact();
    }

    private static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
