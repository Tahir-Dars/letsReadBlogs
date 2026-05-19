package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.PostCreationRequest;
import com.letsreadhere.blog.domain.UpdatePostRequest;
import com.letsreadhere.blog.domain.model.Posts;
import com.letsreadhere.blog.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Posts> getAllPosts(UUID categoryId, UUID tagId);

    List<Posts> getDraftPosts(User user);

    Posts createPost(User user, PostCreationRequest postCreationRequest);

    Posts updatePost(UpdatePostRequest request, UUID uuid);
}
