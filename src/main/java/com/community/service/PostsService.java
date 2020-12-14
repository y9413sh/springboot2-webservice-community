package com.community.service;

import com.community.domain.posts.Posts;
import com.community.domain.posts.PostsRepository;
import com.community.web.dto.posts.PostsResponseDto;
import com.community.web.dto.posts.PostsSaveRequestDto;
import com.community.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public Page<Posts> getPostsList(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    @Transactional
    public Boolean getListCheck(Pageable pageable) {
        Page<Posts> saved = getPostsList(pageable);
        if(pageable.getPageNumber() == 0) {
            pageable.next().getPageNumber();
        }
            Boolean check = saved.hasNext();

        return check;
    }

    /*@Transactional
    public int updateView(Long id) {
        return postsRepository.updateView(id);
    }*/

    @Transactional
    public List<Posts> search(String keyword, Pageable pageable) {
        List<Posts> postsList = postsRepository.findByTitleContaining(keyword, pageable);
        return postsList;
    }


}