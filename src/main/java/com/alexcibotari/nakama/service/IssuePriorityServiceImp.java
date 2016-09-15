package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.repository.IssuePriorityRepository;
import com.alexcibotari.nakama.web.rest.resource.IssuePriorityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssuePriorityServiceImp implements IssuePriorityService {


    @Autowired
    IssuePriorityRepository priorityRepository;


    public IssuePriority findOne(Long id) {
        return priorityRepository.findOne(id);
    }

    public List<IssuePriority> findAll() {
        return (List<IssuePriority>) priorityRepository.findAll();
    }

    @Transactional
    public IssuePriority create(IssuePriorityResource resource) {
        IssuePriority priority = new IssuePriority();
        priority.setName(resource.getName());
        priority.setDescription(resource.getDescription());
        return priorityRepository.save(priority);
    }


    @Transactional
    public IssuePriority update(Long id, IssuePriorityResource resource) {
        IssuePriority priority = findOne(id);
        priority.setName(resource.getName());
        priority.setDescription(resource.getDescription());
        return priorityRepository.save(priority);
    }

    @Transactional
    public void delete(Long id) {
        priorityRepository.delete(id);
    }

}
