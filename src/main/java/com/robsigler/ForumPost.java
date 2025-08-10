package com.robsigler;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@MappedEntity
@Data
@Builder
@Serdeable
public class ForumPost {
    @Id
    @AutoPopulated
    private UUID id;

    private String body;
}
