package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.web.rest.resource.IssueStatusResource;

import java.util.List;

public interface IssueStatusService {

    IssueStatus findOne(Long id);

    List<IssueStatus> findAll();

    IssueStatus create(IssueStatusResource resource);

    IssueStatus update(Long id, IssueStatusResource resource);

    void delete(Long id);
}
