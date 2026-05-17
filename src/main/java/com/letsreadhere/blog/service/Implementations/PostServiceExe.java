package com.letsreadhere.blog.service.Implementations;

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

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceExe implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagsService tagsService;

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
}
