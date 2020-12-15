package com.community.web.dto.posts;

import com.community.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;

    public PostsListResponseDto(Posts entity) {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.author=entity.getAuthor();
    }
}
