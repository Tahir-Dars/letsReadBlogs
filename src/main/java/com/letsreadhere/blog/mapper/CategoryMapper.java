package com.letsreadhere.blog.mapper;


import com.letsreadhere.blog.domain.PostStatus;
import com.letsreadhere.blog.domain.dto.CategoryCreationDto;
import com.letsreadhere.blog.domain.dto.CategoryDto;
import com.letsreadhere.blog.domain.model.Category;
import com.letsreadhere.blog.domain.model.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatedPostCount")
    CategoryDto entityToDto(Category category);

    Category DtoToEntity(CategoryCreationDto categoryCreationDto);

    @Named("calculatedPostCount")
    default long calculatedPostCount(List<Posts> posts) {
        if (posts==null) {
            return 0;
        }
        return posts.stream()
                .filter(posts1 -> PostStatus.PUBLISHED.equals(posts1.getPostStatus()))
                .count();
    }
}
