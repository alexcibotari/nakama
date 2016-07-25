package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.repository.IssuePriorityRepository;
import com.alexcibotari.nakama.web.rest.dto.IssuePriorityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssuePriorityServiceImp implements IssuePriorityService {


    @Autowired
    IssuePriorityRepository issuePriorityRepository;


    public IssuePriority findOne(Long id) {
        return issuePriorityRepository.findOne(id);
    }

    public List<IssuePriority> findAll() {
        return (List<IssuePriority>) issuePriorityRepository.findAll();
    }

    @Transactional
    public IssuePriority create(IssuePriorityDTO dto) {
        IssuePriority issuePriority = new IssuePriority();
        issuePriority.setName(dto.getName());
        issuePriority.setDescription(dto.getDescription());
        return issuePriorityRepository.save(issuePriority);
    }


    @Transactional
    public IssuePriority update(IssuePriorityDTO dto) {
        IssuePriority issuePriority = findOne(dto.getId());
        issuePriority.setName(dto.getName());
        issuePriority.setDescription(dto.getDescription());
        return issuePriorityRepository.save(issuePriority);
    }

    @Transactional
    public void delete(Long id) {
        issuePriorityRepository.delete(id);
    }

}
