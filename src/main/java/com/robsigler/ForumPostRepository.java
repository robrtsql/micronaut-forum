package com.robsigler;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface ForumPostRepository extends ReactiveStreamsCrudRepository<ForumPost, UUID> {}
