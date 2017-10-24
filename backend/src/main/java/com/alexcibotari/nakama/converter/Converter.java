package com.alexcibotari.nakama.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

interface Converter<ENTRY,DTO> {

    ENTRY createFromDto(DTO dto);

    DTO createFromEntry(ENTRY entity);

    ENTRY updateEntity(ENTRY entity, DTO dto);

    default List<DTO> createFromEntities(final Collection<? extends ENTRY> entities) {
        Assert.notNull(entities, "Collection must be not null");
        return entities.stream()
            .map(this::createFromEntry)
            .collect(Collectors.toList());
    }

    default List<ENTRY> createFromDtos(final Collection<? extends DTO> dtos) {
        Assert.notNull(dtos, "Collection must be not null");
        return dtos.stream()
            .map(this::createFromDto)
            .collect(Collectors.toList());
    }
}
