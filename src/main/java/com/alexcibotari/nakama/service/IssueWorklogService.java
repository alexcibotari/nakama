package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueWorklog;
import com.alexcibotari.nakama.web.rest.dto.IssueWorklogDTO;

import java.util.List;

public interface IssueWorklogService {

    IssueWorklog findOne(Long id);

    IssueWorklog findOne(String projectKey, Long idInProject, Long idInIssue);

    List<IssueWorklog> findAll();

    List<IssueWorklog> findAllInIssue(String projectKey, Long idInProject);

    List<IssueWorklog> findAllInIssue(String key);

    IssueWorklog create(IssueWorklogDTO dto);

    IssueWorklog update(IssueWorklogDTO dto);

    void delete(Long id);

    void delete(String projectKey, Long idInProject, Long idInIssue);
}
