package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> listAllCategories();

    Category createACategory(Category category);

    void deleteCategory(UUID uuid);
}
