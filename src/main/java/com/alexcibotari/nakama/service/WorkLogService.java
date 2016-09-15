package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.WorkLog;
import com.alexcibotari.nakama.web.rest.resource.WorkLogResource;

import java.util.List;

public interface WorkLogService {

    WorkLog findOne(Long id);

    List<WorkLog> findAll();

    List<WorkLog> findAllByIssue(String projectKey, Long idInProject);

    List<WorkLog> findAllByIssue(String key);

    WorkLog create(WorkLogResource resource);

    WorkLog update(Long id, WorkLogResource resource);

    void delete(Long id);

    void delete(WorkLog workLog);
}
