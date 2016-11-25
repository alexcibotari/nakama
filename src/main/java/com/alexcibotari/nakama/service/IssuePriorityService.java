package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.web.rest.resource.IssuePriorityResource;

import java.util.List;
import java.util.Optional;

public interface IssuePriorityService {

    Optional<IssuePriority> findOne(Long id);

    List<IssuePriority> findAll();

    IssuePriority create(IssuePriorityResource resource);

    Optional<IssuePriority> update(Long id, IssuePriorityResource resource);

    Optional<IssuePriority> delete(Long id);
}
