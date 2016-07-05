package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public Optional<Project> findOneById(Long id){
        return projectRepository.findOneById(id);
    }

    public List<Project> findAll(){
        return (List<Project>) projectRepository.findAll();
    }


}