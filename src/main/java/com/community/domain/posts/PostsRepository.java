package com.community.domain.posts;

import com.community.web.dto.posts.PostsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p from Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    List<Posts> findByTitleContaining(String keyword);
}
