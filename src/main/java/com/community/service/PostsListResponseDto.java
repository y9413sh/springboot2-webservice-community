package com.community.service;

import com.community.domain.posts.Posts;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String createdDate;
    private String modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm"));
        this.modifiedDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm"));
    }
}