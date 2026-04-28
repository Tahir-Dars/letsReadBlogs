package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listAllCategories();

    Category createACategory(Category category);
}
