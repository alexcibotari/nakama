package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;

import java.util.List;

public interface IssueStatusService {

    IssueStatus findOne(Long id);

    List<IssueStatus> findAll();
}
