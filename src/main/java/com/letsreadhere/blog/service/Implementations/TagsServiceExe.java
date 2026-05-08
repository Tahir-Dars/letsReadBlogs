package com.letsreadhere.blog.service.Implementations;

import com.letsreadhere.blog.domain.model.Tag;
import com.letsreadhere.blog.repository.TagsRepository;
import com.letsreadhere.blog.service.TagsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagsServiceExe implements TagsService {

    private final TagsRepository tagsRepository;

    @Override
    public List<Tag> getTags() {
        return tagsRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTag(Set<String> tagNames) {
        List<Tag> existingTags = tagsRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream().map(Tag::getName).collect(Collectors.toSet());
        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build()
                ).toList();
        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = tagsRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);
        return savedTags;
    }


}
