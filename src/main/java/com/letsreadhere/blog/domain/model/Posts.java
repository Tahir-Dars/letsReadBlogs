package com.letsreadhere.blog.domain.model;

import com.letsreadhere.blog.domain.PostStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank
    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String content;

//    private User author;

    @NotBlank
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Column(nullable = false)
    private Integer readingTime;

    @NotBlank
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @NotBlank
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
