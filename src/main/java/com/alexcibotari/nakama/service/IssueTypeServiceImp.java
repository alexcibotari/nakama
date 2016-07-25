package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.repository.IssueTypeRepository;
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

}
