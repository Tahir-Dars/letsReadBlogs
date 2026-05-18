package com.letsreadhere.blog.service.impl;

import com.letsreadhere.blog.domain.PostCreationRequest;
import com.letsreadhere.blog.domain.PostStatus;
import com.letsreadhere.blog.domain.model.Category;
import com.letsreadhere.blog.domain.model.Posts;
import com.letsreadhere.blog.domain.model.Tag;
import com.letsreadhere.blog.domain.model.User;
import com.letsreadhere.blog.repository.PostRepository;
import com.letsreadhere.blog.service.CategoryService;
import com.letsreadhere.blog.service.PostService;
import com.letsreadhere.blog.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceExe implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagsService tagsService;


    private static final int WORDS_PER_MINUTE = 200;

    @Override
    @Transactional(readOnly = true)
    public List<Posts> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagsService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndCategoryItBelongsToAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByPostStatusAndCategoryItBelongsTo(
                    PostStatus.PUBLISHED,
                    category
            );
        }
        if (tagId != null) {
            Tag tag = tagsService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag
            );
        }

        return postRepository.findAllByPostStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Posts> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndPostStatus(user, PostStatus.DRAFT);
    }

    @Override
    @Transactional
    public Posts createPost(User user, PostCreationRequest postCreationRequest) {
        Posts newPost = new Posts();
        newPost.setTitle(postCreationRequest.getTitle());
        newPost.setContent(postCreationRequest.getContent());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculatedReadingTime(postCreationRequest.getContent()));
        Category category = categoryService.getCategoryById(postCreationRequest.getCategoryId());
        newPost.setCategoryItBelongsTo(category);

        Set<UUID> tagIds = postCreationRequest.getTagIds();
        List<Tag> tagList = tagsService.getTagByIds(tagIds);
        newPost.setTags((new HashSet<>(tagList)));
        newPost.setPostStatus(PostStatus.PUBLISHED);

        return postRepository.save(newPost);
    }

    private Integer calculatedReadingTime(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }
        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }
}
