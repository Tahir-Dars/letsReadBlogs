package com.letsreadhere.blog.service.Implementations;

import com.letsreadhere.blog.domain.model.Tag;
import com.letsreadhere.blog.repository.TagsRepository;
import com.letsreadhere.blog.service.TagsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public Tag deleteTag(UUID id) {
        Optional<Tag> optionalTag = tagsRepository.findById(id);
        if (optionalTag.isPresent()) {
            if (!optionalTag.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Can't delete a Tag with Post");
            }
            tagsRepository.deleteById(id);
            return optionalTag.get();
        }
        throw new IllegalArgumentException("This ID does not exist !!");
    }

    @Override
    public Tag getTagById(UUID uuid) {
        return tagsRepository.findById(uuid)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category not found with ID: " + uuid));
    }

}
