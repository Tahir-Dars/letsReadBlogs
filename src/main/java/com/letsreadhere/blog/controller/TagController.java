package com.letsreadhere.blog.controller;

import com.letsreadhere.blog.domain.dto.TagsCreationDto;
import com.letsreadhere.blog.domain.dto.TagsResponse;
import com.letsreadhere.blog.domain.model.Tag;
import com.letsreadhere.blog.mapper.TagMapper;
import com.letsreadhere.blog.service.TagsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<List<TagsResponse>> createATag(@Valid @RequestBody TagsCreationDto dto) {
        List<Tag> savedTags = tagsService.createTag(dto.getNames());
        List<TagsResponse> tagsResponses = savedTags.stream().map(tagMapper::toTagResponse).toList();
        return new ResponseEntity<>(
                tagsResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<TagsResponse> deleteTag(@PathVariable UUID id) {
        Tag tag = tagsService.deleteTag(id);
        TagsResponse tagsResponse=tagMapper.toTagResponse(tag);
        return new ResponseEntity<>(tagsResponse,HttpStatus.GONE);
    }
}
