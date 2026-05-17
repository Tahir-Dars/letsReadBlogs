package com.letsreadhere.blog.controller;


import com.letsreadhere.blog.domain.dto.PostResponseDto;
import com.letsreadhere.blog.domain.model.Posts;
import com.letsreadhere.blog.mapper.PostMapper;
import com.letsreadhere.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId
    ) {
        List<Posts> postsList = postService.getAllPosts(categoryId, tagId);
        List<PostResponseDto> postResponseDto = postsList.stream().map(postMapper::PostToPostDto).
                toList();
        return ResponseEntity.ok(postResponseDto);
    }
}
