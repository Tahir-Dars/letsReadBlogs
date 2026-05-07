package com.letsreadhere.blog.controller;


import com.letsreadhere.blog.domain.dto.AuthResponse;
import com.letsreadhere.blog.domain.dto.LoginRequest;
import com.letsreadhere.blog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> authResponseResponseEntity(
            @RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String JwtToken = authenticationService.generateToken(userDetails);

        AuthResponse authResponse = AuthResponse.builder()
                .token(JwtToken)
                .expiresIn(86400)
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
