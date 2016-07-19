package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.repository.IssueStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueStatusServiceImp implements IssueStatusService {


    @Autowired
    IssueStatusRepository issueStatusRepository;


    public IssueStatus findOne(Long id) {
        return issueStatusRepository.findOne(id);
    }


    public List<IssueStatus> findAll() {
        return (List<IssueStatus>) issueStatusRepository.findAll();
    }

}
