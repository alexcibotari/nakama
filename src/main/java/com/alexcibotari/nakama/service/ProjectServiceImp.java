package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.repository.ProjectRepository;
import com.alexcibotari.nakama.security.SecurityUtils;
import com.alexcibotari.nakama.web.rest.dto.ProjectDTO;
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

    public Project findOneById(Long id){
        return projectRepository.findOneById(id);
    }
    public Project findOneByKey(String key){
        return projectRepository.findOneByKey(key);
    }

    public List<Project> findAll(){
        return (List<Project>) projectRepository.findAll();
    }

    public Project create(ProjectDTO dto){
        Project project = new Project();
        project.setKey(dto.getKey());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return projectRepository.save(project);
    }

    public Project update(ProjectDTO dto){
        Project project = projectRepository.findOneByKey(dto.getKey());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return projectRepository.save(project);
    }

    public void delete(String key){
        projectRepository.deleteOneByKey(key);
    }

}