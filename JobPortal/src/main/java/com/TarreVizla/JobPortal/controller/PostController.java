package com.TarreVizla.JobPortal.controller;

import com.TarreVizla.JobPortal.model.Post;
import com.TarreVizla.JobPortal.repository.PostRepository;
import com.TarreVizla.JobPortal.repository.SearchRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    SearchRepository searchRepository;

    @RequestMapping(value = "/")
    @Hidden
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }

    @GetMapping("/allPosts")
    public List<Post> getAllPosts(){
        List<Post> value = postRepository.findAll();
        return value;
    }

    @GetMapping("/posts/{keyword}")
    public List<Post> searchPost(@PathVariable String keyword){
        return searchRepository.findByKeyword(keyword);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return postRepository.save(post);
    }
}
