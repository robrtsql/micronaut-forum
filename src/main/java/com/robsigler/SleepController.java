package com.robsigler;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import jakarta.inject.Inject;

import reactor.core.publisher.Mono;

import java.time.Duration;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/sleep")
public class SleepController {
    @Inject
    private ForumPostRepository postRepository;

    @Inject
    private ForumPostJdbcRepository forumPostJdbcRepository;

    @Get("/")
    public Mono<Void> reactor() {
        return Mono.delay(Duration.ofSeconds(8)).then();
    }

    @Get("/list/block")
    @ExecuteOn("VIRTUAL")
    public void sleepBlock() {
        Mono.delay(Duration.ofSeconds(8)).then().block();
    }

    @Get("/list")
    public void sleepSync() throws InterruptedException {
        Thread.sleep(8000);
    }
}
