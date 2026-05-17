package com.letsreadhere.blog.repository;

import com.letsreadhere.blog.domain.PostStatus;
import com.letsreadhere.blog.domain.model.Category;
import com.letsreadhere.blog.domain.model.Posts;
import com.letsreadhere.blog.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Posts, UUID> {

    List<Posts> findAllByPostStatusAndCategoryItBelongsToAndTagsContaining(PostStatus status, Category category, Tag tag);

    List<Posts> findAllByPostStatusAndCategoryItBelongsToContaining(PostStatus status, Category category);

    List<Posts> findAllByPostStatusAndTagsContaining(PostStatus status, Tag tags);

    List<Posts> findAllByPostStatus(PostStatus Status);
}
