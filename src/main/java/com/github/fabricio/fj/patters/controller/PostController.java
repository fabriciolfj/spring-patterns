package com.github.fabricio.fj.patters.controller;

import com.github.fabricio.fj.patters.request.PostRequest;
import com.github.fabricio.fj.patters.responses.PostResponse;
import com.github.fabricio.fj.patters.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponse> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponse create(@PathVariable("id") final Long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public PostResponse update(@PathVariable("id") final Long id, @RequestBody final PostRequest request) {
        return postService.update(request, id);
    }

    @PostMapping
    public PostResponse create(@RequestBody final PostRequest request) {
        return postService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) {
        postService.delete(id);
    }
}
