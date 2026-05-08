package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagsService {
    List<Tag> getTags();

    List<Tag> createTag(Set<String> tagNames);

}
