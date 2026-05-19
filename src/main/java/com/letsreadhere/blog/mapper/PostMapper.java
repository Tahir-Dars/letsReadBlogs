package com.letsreadhere.blog.mapper;

import com.letsreadhere.blog.domain.PostCreationRequest;
import com.letsreadhere.blog.domain.UpdatePostRequest;
import com.letsreadhere.blog.domain.dto.PostCreationDto;
import com.letsreadhere.blog.domain.dto.PostResponseDto;
import com.letsreadhere.blog.domain.dto.UpdatePostRequestDto;
import com.letsreadhere.blog.domain.model.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(source = "author", target = "authorDto")
    @Mapping(source = "categoryItBelongsTo", target = "categoryDto")
    @Mapping(source = "tags", target = "tags")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "postStatus", target = "postStatus")
    PostResponseDto PostToPostDto(Posts posts);

    PostCreationRequest dtoToSimpleRequest(PostCreationDto dto);

    @Mapping(source = "status", target = "postStatus")
    UpdatePostRequest dtoToSimpleRequestForUpdate(UpdatePostRequestDto dto);
}
