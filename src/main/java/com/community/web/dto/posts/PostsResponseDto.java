package com.community.web.dto.posts;

import com.community.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private int views;

    public PostsResponseDto(Posts entity) {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.author=entity.getAuthor();
        this.content=entity.getContent();
        this.views=entity.getViews();
    }
}
