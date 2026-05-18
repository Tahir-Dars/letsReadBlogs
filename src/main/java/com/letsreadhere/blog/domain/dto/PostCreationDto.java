package com.letsreadhere.blog.domain.dto;

import com.letsreadhere.blog.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreationDto {
    @NotBlank(message = "Title can not be empty !!")
    @Size(min = 4, max = 100, message = "Title must be between {min} and {max}")
    private String title;

    @NotBlank(message = "Content cant be empty")
    @Size(min = 15, max = 50000, message = "Cotent must be between {min} and {max}")
    private String content;

    @NotNull(message = "CategoryId is required")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10, message = "Maximum Tags allowed are:{max}")
    private Set<UUID> tagIds = new HashSet<>();

    @NotNull(message = "Status is required")
    private PostStatus status;

}
