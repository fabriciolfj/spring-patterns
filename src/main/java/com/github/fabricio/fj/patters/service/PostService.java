package com.github.fabricio.fj.patters.service;

import com.github.fabricio.fj.patters.request.PostRequest;
import com.github.fabricio.fj.patters.responses.PostResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;

    public PostService() {
        restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    public List<PostResponse> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public PostResponse findById(final Long id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .body(PostResponse.class);
    }

    public PostResponse create(final PostRequest request) {
        return restClient.post()
                .uri("/posts")
                .body(request)
                .retrieve()
                .body(PostResponse.class);
    }

    public PostResponse update(final PostRequest request, final Long id) {
        return restClient.put()
                .uri("/posts/{id}", id)
                .body(request)
                .retrieve()
                .body(PostResponse.class);
    }

    public void delete(final Long id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
