package com.letsreadhere.blog.service.impl;

import com.letsreadhere.blog.domain.model.User;
import com.letsreadhere.blog.repository.UserRepository;
import com.letsreadhere.blog.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceExe implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid).
                orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + uuid));
    }
}
