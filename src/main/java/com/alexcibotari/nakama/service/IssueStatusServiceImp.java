package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.repository.IssueStatusRepository;
import com.alexcibotari.nakama.web.rest.resource.IssueStatusResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueStatusServiceImp implements IssueStatusService {


    @Autowired
    IssueStatusRepository statusRepository;


    public IssueStatus findOne(Long id) {
        return statusRepository.findOne(id);
    }


    public List<IssueStatus> findAll() {
        return (List<IssueStatus>) statusRepository.findAll();
    }

    @Transactional
    public IssueStatus create(IssueStatusResource resource) {
        IssueStatus status = new IssueStatus();
        status.setName(resource.getName());
        status.setDescription(resource.getDescription());
        return statusRepository.save(status);
    }

    @Transactional
    public IssueStatus update(Long id, IssueStatusResource resource) {
        IssueStatus status = findOne(id);
        status.setName(resource.getName());
        status.setDescription(resource.getDescription());
        return statusRepository.save(status);
    }

    @Transactional
    public void delete(Long id) {
        statusRepository.delete(id);
    }
}
