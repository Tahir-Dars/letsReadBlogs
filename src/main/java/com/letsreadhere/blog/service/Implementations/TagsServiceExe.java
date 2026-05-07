package com.letsreadhere.blog.service.Implementations;

import com.letsreadhere.blog.domain.model.Tag;
import com.letsreadhere.blog.repository.TagsRepository;
import com.letsreadhere.blog.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagsServiceExe implements TagsService {

    private final TagsRepository tagsRepository;

    @Override
    public List<Tag> getTags() {
        return tagsRepository.findAllWithPostCount();
    }
}
