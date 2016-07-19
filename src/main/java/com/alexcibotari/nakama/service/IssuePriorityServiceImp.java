package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.repository.IssuePriorityRepository;
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

}
