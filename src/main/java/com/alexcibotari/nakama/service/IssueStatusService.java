package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.web.resource.IssueStatusResource;

import java.util.List;
import java.util.Optional;

public interface IssueStatusService {

    Optional<IssueStatus> findOne(Long id);

    List<IssueStatus> findAll();

    IssueStatus create(IssueStatusResource resource);

    Optional<IssueStatus> update(Long id, IssueStatusResource resource);

    Optional<IssueStatus> delete(Long id);
}
