package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;

import java.util.List;

public interface IssuePriorityService {

    IssuePriority findOne(Long id);

    List<IssuePriority> findAll();
}
