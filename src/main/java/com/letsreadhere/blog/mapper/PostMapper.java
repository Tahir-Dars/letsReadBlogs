package com.letsreadhere.blog.mapper;

import com.letsreadhere.blog.domain.dto.PostResponseDto;
import com.letsreadhere.blog.domain.model.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(source = "author", target = "authorDto")
    @Mapping(source = "categoryItBelongsTo", target = "categoryDto")
    @Mapping(source = "tags", target = "tags")
    PostResponseDto PostToPostDto(Posts posts);
}
