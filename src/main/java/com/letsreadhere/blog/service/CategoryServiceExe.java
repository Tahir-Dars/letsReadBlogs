package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.Category;
import com.letsreadhere.blog.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceExe implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createACategory(Category category) {
        if (!categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists with : " + category.getName());
        }
        return categoryRepository.save(category);
    }
}
