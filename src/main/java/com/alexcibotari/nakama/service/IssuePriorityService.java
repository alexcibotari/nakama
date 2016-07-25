package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.web.rest.dto.IssuePriorityDTO;

import java.util.List;

public interface IssuePriorityService {

    IssuePriority findOne(Long id);

    List<IssuePriority> findAll();

    IssuePriority create(IssuePriorityDTO dto);

    IssuePriority update(IssuePriorityDTO dto);

    void delete(Long id);
}
