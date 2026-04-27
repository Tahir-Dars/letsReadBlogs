package com.letsreadhere.blog.controller;


import com.letsreadhere.blog.domain.dto.CategoryDto;
import com.letsreadhere.blog.mapper.CategoryMapper;
import com.letsreadhere.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.listAllCategories()
                .stream().map(categoryMapper::entityToDto)
                .toList();
        return ResponseEntity.ok(categories);

    }
}
