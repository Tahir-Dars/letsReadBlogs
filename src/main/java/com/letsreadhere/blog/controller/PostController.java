package com.letsreadhere.blog.controller;


import com.letsreadhere.blog.domain.PostCreationRequest;
import com.letsreadhere.blog.domain.UpdatePostRequest;
import com.letsreadhere.blog.domain.dto.PostCreationDto;
import com.letsreadhere.blog.domain.dto.PostResponseDto;
import com.letsreadhere.blog.domain.dto.UpdatePostRequestDto;
import com.letsreadhere.blog.domain.model.Posts;
import com.letsreadhere.blog.domain.model.User;
import com.letsreadhere.blog.mapper.PostMapper;
import com.letsreadhere.blog.service.PostService;
import com.letsreadhere.blog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

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

    @GetMapping("/drafts")
    public ResponseEntity<List<PostResponseDto>> getDraftedPosts(
            @RequestAttribute("userId") UUID userId
    ) {
        User loggedUser = userService.getUserById(userId);
        List<Posts> draftPosts = postService.getDraftPosts(loggedUser);
        List<PostResponseDto> postResponseDto = draftPosts.stream().map(postMapper::PostToPostDto).toList();
        return ResponseEntity.ok(postResponseDto);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @Valid @RequestBody PostCreationDto postCreationDto,
            @RequestAttribute("userId") UUID uuid
    ) {
        User loggedInUser = userService.getUserById(uuid);
        PostCreationRequest postCreationRequest = postMapper.dtoToSimpleRequest(postCreationDto);
        Posts postCreated = postService.createPost(loggedInUser, postCreationRequest);
        PostResponseDto createdPostResponse = postMapper.PostToPostDto(postCreated);

        return new ResponseEntity<>(createdPostResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostResponseDto> UpdatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto
    ) {
        UpdatePostRequest updatePostRequest = postMapper.
                dtoToSimpleRequestForUpdate(updatePostRequestDto);
        Posts updatedposts = postService.updatePost(updatePostRequest, id);
        PostResponseDto responseDto = postMapper.PostToPostDto(updatedposts);

        return ResponseEntity.ok(responseDto);
    }
}
