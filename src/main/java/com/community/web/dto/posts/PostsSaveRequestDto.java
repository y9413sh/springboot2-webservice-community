package com.community.web.dto.posts;

import com.community.domain.posts.Posts;
import com.community.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String author;
    private String content;
    private Long views;

    @Builder
    public PostsSaveRequestDto(String title, String author, String content, Long views) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.views = views;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .views(0)
                .build();
    }

}
