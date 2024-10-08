package com.robsigler;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/posts")
public class ForumPostController {
  @Inject private ForumPostRepository postRepository;

  @Get("/")
  public Flux<ForumPost> list() {
    return Flux.from(postRepository.findAll());
  }

  @Post("/")
  public Mono<Void> create(@Body ForumPost post) {
    return Flux.from(postRepository.save(post)).then();
  }
}
