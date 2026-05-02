package com.letsreadhere.blog.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationManager {
    UserDetails authenticateUser(String email, String password);

    String generateToken(UserDetails userDetails);
}
