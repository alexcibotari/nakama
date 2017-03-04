package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.repository.IssueTypeRepository;
import com.alexcibotari.nakama.web.resource.IssueTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IssueTypeServiceImp implements IssueTypeService {


    @Autowired
    IssueTypeRepository issueTypeRepository;


    public Optional<IssueType> findOne(Long id) {
        return issueTypeRepository.findOneById(id);
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
    public Optional<IssueType> update(Long id, IssueTypeResource resource) {
        return findOne(id)
            .map(entity -> {
                entity.setName(resource.getName());
                entity.setDescription(resource.getDescription());
                return issueTypeRepository.save(entity);
            });
    }

    @Transactional
    public Optional<IssueType> delete(Long id) {
        return issueTypeRepository.deleteOneById(id);
    }
}
