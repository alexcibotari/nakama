package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.web.rest.dto.IssueDTO;

import java.util.List;

public interface IssueService {

    Issue findOne(Long id);

    Issue findOne(String projectKey, Long idInProject);

    Issue findOne(String key);

    List<Issue> findAllByProjectId(Long id);

    List<Issue> findAllByProjectKey(String key);

    List<Issue> findAll();

    Issue create(IssueDTO dto);

    Issue update(IssueDTO dto);

    void delete(Long id);

    void delete(String projectKey, Long idInProject);
}
