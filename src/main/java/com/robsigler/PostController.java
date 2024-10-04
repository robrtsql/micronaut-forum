package com.robsigler;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class PostController {
    @Inject
    private PostRepository postRepository;

    @Get("/")
    public Flux<Post> list() {
        return Flux.from(postRepository.findAll());
    }

    @io.micronaut.http.annotation.Post("/")
    public Mono<Void> create(@Body Post post) {
        return Flux.from(postRepository.save(post)).then();
    }
}
