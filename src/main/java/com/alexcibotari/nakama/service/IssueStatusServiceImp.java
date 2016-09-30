package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.repository.IssueStatusRepository;
import com.alexcibotari.nakama.web.rest.resource.IssueStatusResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IssueStatusServiceImp implements IssueStatusService {


    @Autowired
    IssueStatusRepository statusRepository;


    public Optional<IssueStatus> findOne(Long id) {
        return statusRepository.findOneById(id);
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
    public Optional<IssueStatus> update(Long id, IssueStatusResource resource) {
        return findOne(id)
            .map(entity -> {
                entity.setName(resource.getName());
                entity.setDescription(resource.getDescription());
                return statusRepository.save(entity);
            });

    }

    @Transactional
    public Optional<IssueStatus> delete(Long id) {
        return statusRepository.deleteOneById(id);
    }
}
