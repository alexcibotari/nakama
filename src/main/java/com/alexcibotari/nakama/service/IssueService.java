package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.web.rest.resource.IssueResource;

import java.util.List;

public interface IssueService {

    Issue findOne(Long id);

    Issue findOne(String projectKey, Long idInProject);

    Issue findOne(String key);

    List<Issue> findAllByProjectId(Long id);

    List<Issue> findAllByProjectKey(String key);

    List<Issue> findAll();

    Issue create(IssueResource resource);

    Issue update(String key, IssueResource resource);

    void delete(Long id);

    void delete(String key);

    void delete(String projectKey, Long idInProject);

    Issue recalculateTimeSpent(Issue issue);
}
