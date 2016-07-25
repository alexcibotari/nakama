package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.repository.ProjectRepository;
import com.alexcibotari.nakama.web.rest.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project findOne(Long id) {
        return projectRepository.findOne(id);
    }

    public Project findOneByKey(String key) {
        return projectRepository.findOneByKey(key);
    }

    public List<Project> findAll() {
        return (List<Project>) projectRepository.findAll();
    }

    @Transactional
    public Project create(ProjectDTO dto) {
        Project project = new Project();
        project.setKey(dto.getKey());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return projectRepository.save(project);
    }

    @Transactional
    public Project update(ProjectDTO dto) {
        Project project = projectRepository.findOne(dto.getId());
        project.setKey(dto.getKey());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return projectRepository.save(project);
    }

    @Transactional
    public void delete(Long id) {
        projectRepository.delete(id);
    }

}