package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.web.rest.dto.IssueDTO;

import java.util.List;

public interface IssueService {

    public Issue findOne(Long id);

    public Issue findOne(String projectKey, Long idInProject);

    public List<Issue> findAllByProjectId(Long id);

    public List<Issue> findAllByProjectKey(String key);

    public List<Issue> findAll();

    public Issue create(IssueDTO dto);

    public Issue update(IssueDTO dto);

    public void delete(Long id);

    public void delete(String projectKey, Long idInProject);
}