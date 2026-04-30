package com.letsreadhere.blog.service;

import com.letsreadhere.blog.domain.model.Category;
import com.letsreadhere.blog.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists with : " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(uuid);
        if (categoryOptional.isPresent()) {
            if (categoryOptional.get().getPosts().isEmpty()) {
                categoryRepository.deleteById(uuid);
                return;
            }
            throw new IllegalStateException("Category has posts published !!");
        }
            throw new IllegalArgumentException("No Such Category Exist");
    }
}
