package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID uuid);
}
