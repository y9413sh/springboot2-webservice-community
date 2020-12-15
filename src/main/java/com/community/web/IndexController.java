package com.community.web;

import com.community.config.auth.dto.SessionUser;
import com.community.domain.posts.Posts;
import com.community.service.PostsService;
import com.community.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Long id, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        SessionUser user=(SessionUser)httpSession.getAttribute("user");

        model.addAttribute("posts", postsService.getPostsList(pageable));
        model.addAttribute("checkNext", postsService.getNextCheck(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("pageNum", pageable.getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        if(user!=null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
        }

        return "index";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model) {

        model.addAttribute("searchList", postsService.search(keyword));
        return "posts-search";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        SessionUser user=(SessionUser)httpSession.getAttribute("user");

        model.addAttribute("userEmail", user.getEmail());

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        SessionUser user=(SessionUser)httpSession.getAttribute("user");

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        if(dto.getAuthor().equals(user.getEmail())) {
            return "posts-update";
        } else {
            return "redirect";
        }
    }

    @GetMapping("/posts/view/{id}")
    public String postsView(@PathVariable Long id, Model model) {
        SessionUser user=(SessionUser)httpSession.getAttribute("user");
        model.addAttribute("userEmail", user.getEmail());
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        postsService.updateView(id);

        return "posts-view";
    }
}