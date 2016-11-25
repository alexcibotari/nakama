package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.repository.IssuePriorityRepository;
import com.alexcibotari.nakama.web.rest.resource.IssuePriorityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IssuePriorityServiceImp implements IssuePriorityService {


    @Autowired
    IssuePriorityRepository priorityRepository;


    public Optional<IssuePriority> findOne(Long id) {
        return priorityRepository.findOneById(id);
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
    public Optional<IssuePriority> update(Long id, IssuePriorityResource resource) {
        return findOne(id)
            .map(entity -> {
                entity.setName(resource.getName());
                entity.setDescription(resource.getDescription());
                return priorityRepository.save(entity);
            });
    }

    @Transactional
    public Optional<IssuePriority> delete(Long id) {
        return priorityRepository.deleteOneById(id);
    }

}
