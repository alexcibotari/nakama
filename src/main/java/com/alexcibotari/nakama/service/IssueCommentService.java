package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueComment;
import com.alexcibotari.nakama.web.rest.dto.IssueCommentDTO;

import java.util.List;

public interface IssueCommentService {

    IssueComment findOne(Long id);

    List<IssueComment> findAll();

    List<IssueComment> findAllInIssue(String projectKey, Long idInProject);

    List<IssueComment> findAllInIssue(String key);

    IssueComment create(IssueCommentDTO dto);

    IssueComment update(IssueCommentDTO dto);

    void delete(Long id);
}
