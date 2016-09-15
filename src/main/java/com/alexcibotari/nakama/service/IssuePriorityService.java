package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.web.rest.resource.IssuePriorityResource;

import java.util.List;

public interface IssuePriorityService {

    IssuePriority findOne(Long id);

    List<IssuePriority> findAll();

    IssuePriority create(IssuePriorityResource resource);

    IssuePriority update(Long id, IssuePriorityResource resource);

    void delete(Long id);
}
