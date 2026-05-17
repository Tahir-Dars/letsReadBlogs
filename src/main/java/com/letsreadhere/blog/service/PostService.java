package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.Posts;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Posts> getAllPosts(UUID categoryId, UUID tagId);
}
