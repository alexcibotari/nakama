package com.alexcibotari.nakama.converter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

interface Converter<E, R> {

  E toEntry(R resource);

  R toResource(E entity);

  E updateEntity(E entity, R resource);

  default List<R> toResources(final Collection<? extends E> entities) {
    Assert.notNull(entities, "Collection must be not null");
    return entities.stream()
      .filter(Objects::nonNull)
      .map(this::toResource)
      .collect(Collectors.toList());
  }

  default List<E> toEntries(final Collection<? extends R> resources) {
    Assert.notNull(resources, "Collection must be not null");
    return resources.stream()
      .filter(Objects::nonNull)
      .map(this::toEntry)
      .collect(Collectors.toList());
  }
}
