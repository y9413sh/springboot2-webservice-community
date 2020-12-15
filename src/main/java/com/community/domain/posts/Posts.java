package com.community.domain.posts;

import com.community.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100, nullable=false)
    private String title;

    @Column(nullable=false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private int views;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public Posts(String title, String author, String content, int views) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.views = views;
    }
}
