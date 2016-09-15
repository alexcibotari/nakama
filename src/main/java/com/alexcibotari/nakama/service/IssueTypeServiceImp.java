package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.repository.IssueTypeRepository;
import com.alexcibotari.nakama.web.rest.resource.IssueTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueTypeServiceImp implements IssueTypeService {


    @Autowired
    IssueTypeRepository issueTypeRepository;


    public IssueType findOne(Long id) {
        return issueTypeRepository.findOne(id);
    }


    public List<IssueType> findAll() {
        return (List<IssueType>) issueTypeRepository.findAll();
    }

    @Transactional
    public IssueType create(IssueTypeResource resource) {
        IssueType issueType = new IssueType();
        issueType.setName(resource.getName());
        issueType.setDescription(resource.getDescription());
        return issueTypeRepository.save(issueType);
    }

    @Transactional
    public IssueType update(Long id, IssueTypeResource resource) {
        IssueType issueType = findOne(id);
        issueType.setName(resource.getName());
        issueType.setDescription(resource.getDescription());
        return issueTypeRepository.save(issueType);
    }

    @Transactional
    public void delete(Long id) {
        issueTypeRepository.delete(id);
    }
}
