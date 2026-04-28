package com.letsreadhere.blog.controller;


import com.letsreadhere.blog.domain.dto.CategoryCreationDto;
import com.letsreadhere.blog.domain.dto.CategoryDto;
import com.letsreadhere.blog.domain.model.Category;
import com.letsreadhere.blog.mapper.CategoryMapper;
import com.letsreadhere.blog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CategoryCreationDto categoryCreationDto
    ) {
        Category createCategory = categoryMapper.DtoToEntity(categoryCreationDto);
        CategoryDto categoryDto = categoryMapper.entityToDto(categoryService.createACategory(createCategory));
        return ResponseEntity.accepted().body(categoryDto);
    }
}
