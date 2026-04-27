package com.letsreadhere.blog.repository;

import com.letsreadhere.blog.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagsRepository extends JpaRepository<Tag, UUID> {
}
