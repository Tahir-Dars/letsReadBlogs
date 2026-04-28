package com.letsreadhere.blog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreationDto {
    @NotBlank(message = "Category must have a name ")
    @Size(min = 3, max = 70, message = "Category must be in between {min} and {max} ")
    @Pattern(regexp = "^[\\w\\s-]+$",
            message = "Category name can only have letters, numbers, spaces ,and hyphens ")
    private String name;
}
