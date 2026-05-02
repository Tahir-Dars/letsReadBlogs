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

@Service
@RequiredArgsConstructor
public class AuthenticationServiceExe implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${spring.app.jwtExpiryTimeInMs}")
    private Long jwtExpiryMs;

    @Override
    public UserDetails authenticateUser(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return userDetailsService.loadUserByUsername(email);

    }

    @Override
    public String generateToken(UserDetails userDetails) {
       return "at3edoomd-3iskskjdoxnslnd-p5678tyujmfcv8";
    }
}
