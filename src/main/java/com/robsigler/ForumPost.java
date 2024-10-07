package com.robsigler;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@MappedEntity
@Data
@Builder
@Serdeable
public class ForumPost {
  @Id @AutoPopulated private UUID id;

  private String body;
}
