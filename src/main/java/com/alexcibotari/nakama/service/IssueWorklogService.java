package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueWorkLog;
import com.alexcibotari.nakama.web.rest.dto.IssueWorkLogDTO;

import java.util.List;

public interface IssueWorkLogService {

    IssueWorkLog findOne(Long id);

    IssueWorkLog findOne(String projectKey, Long idInProject, Long idInIssue);

    List<IssueWorkLog> findAll();

    List<IssueWorkLog> findAllInIssue(String projectKey, Long idInProject);

    List<IssueWorkLog> findAllInIssue(String key);

    IssueWorkLog create(IssueWorkLogDTO dto);

    IssueWorkLog update(IssueWorkLogDTO dto);

    void delete(Long id);

    void delete(String projectKey, Long idInProject, Long idInIssue);
}
