package com.robsigler;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import jakarta.inject.Inject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/posts")
public class ForumPostController {
    @Inject
    private ForumPostRepository postRepository;

    @Inject
    private ForumPostJdbcRepository forumPostJdbcRepository;

    @Get("/")
    public Flux<ForumPost> list() {
        return Flux.from(postRepository.findAll());
    }

    @Get("/list/block")
    @ExecuteOn("VIRTUAL")
    public List<ForumPost> listBlock() {
        return Flux.from(postRepository.findAll()).collectList().block();
    }

    @Get("/list")
    public List<ForumPost> listSync() {
        return forumPostJdbcRepository.findAll();
    }

    @Post("/")
    public Mono<Void> create(@Body ForumPost post) {
        return Flux.from(postRepository.save(post)).then();
    }
}
