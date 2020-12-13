package com.community.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /*@Modifying
    @Query("update Posts p set p.views = p.views + 1 where p.id = :id")
    int updateView(Long id);*/

    List<Posts> findByTitleContaining(String keyword);
}
