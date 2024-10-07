package com.robsigler;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@MicronautTest(transactional = false)
class ForumTest {

  @Inject EmbeddedApplication<?> application;

  @Inject private ForumPostRepository forumPostRepository;

  @Test
  void testItWorks() {
    Flux.from(forumPostRepository.save(ForumPost.builder().body("Hello World").build()))
        .flatMap(x -> forumPostRepository.findAll())
        .collectList()
        .doOnNext(
            list -> {
              Assertions.assertEquals("Hello World", list.getFirst().getBody());
            })
        .block();
  }
}
