package com.letsreadhere.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CategoryDto {
    private UUID id;
    private String name;
    private long postCount;
}
