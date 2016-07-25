package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.web.rest.dto.IssueStatusDTO;

import java.util.List;

public interface IssueStatusService {

    IssueStatus findOne(Long id);

    List<IssueStatus> findAll();

    IssueStatus create(IssueStatusDTO dto);

    IssueStatus update(IssueStatusDTO dto);

    void delete(Long id);
}
