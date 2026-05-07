package com.letsreadhere.blog.controller;

import com.letsreadhere.blog.domain.dto.TagsResponse;
import com.letsreadhere.blog.mapper.TagMapper;
import com.letsreadhere.blog.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagsService tagsService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagsResponse>> getAllTags() {
        List<TagsResponse> tagList = tagsService.getTags().stream()
                .map(tagMapper::toTagResponse).toList();
        return ResponseEntity.ok(tagList);
    }
}
