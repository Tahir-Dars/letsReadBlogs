package com.letsreadhere.blog.mapper;

import com.letsreadhere.blog.domain.PostStatus;
import com.letsreadhere.blog.domain.dto.TagsResponse;
import com.letsreadhere.blog.domain.model.Posts;
import com.letsreadhere.blog.domain.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatedPostCount")
    TagsResponse toTagResponse(Tag tag);

    @Named("calculatedPostCount")
    default Integer calculatedPostCount(List<Posts> posts) {
        if (posts == null) {
            return 0;
        }
        return (int) posts.stream()
                .filter(posts1 -> PostStatus.PUBLISHED.equals(posts1.getPostStatus()))
                .count();
    }
}
