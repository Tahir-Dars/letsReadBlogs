package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.dto.TagsResponse;
import com.letsreadhere.blog.domain.model.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagsService {
    List<Tag> getTags();

    List<Tag> createTag(Set<String> tagNames);

    Tag deleteTag(UUID id);

    Tag getTagById(UUID uuid);
}
