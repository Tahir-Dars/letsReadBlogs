package com.letsreadhere.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    UserDetails authenticateUser(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String token);
}
