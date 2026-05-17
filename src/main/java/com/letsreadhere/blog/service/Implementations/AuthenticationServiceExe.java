package com.letsreadhere.blog.service.Implementations;

import com.letsreadhere.blog.service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceExe implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    @Value("${spring.app.jwtExpiryTimeInMs}")
    private Long jwtExpiryMs;

    @Value("${spring.app.jwtScret}")
    private String jwtSecret;

    @Override
    public UserDetails authenticateUser(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return userDetailsService.loadUserByUsername(email);

    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public UserDetails validateToken(String token) {
        String username = extractUsernameFromToken(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private String extractUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(getSigningKey())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(jwtSecret);
        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
    }
}
