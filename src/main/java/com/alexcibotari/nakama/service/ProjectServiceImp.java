package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.repository.ProjectRepository;
import com.alexcibotari.nakama.web.rest.resource.ProjectResource;
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
    public Project create(ProjectResource resource) {
        Project project = new Project();
        project.setKey(resource.getKey());
        project.setName(resource.getName());
        project.setDescription(resource.getDescription());
        return projectRepository.save(project);
    }

    @Transactional
    public Project update(Long id, ProjectResource resource) {
        Project project = projectRepository.findOne(id);
        project.setKey(resource.getKey());
        project.setName(resource.getName());
        project.setDescription(resource.getDescription());
        return projectRepository.save(project);
    }

    @Transactional
    public void delete(Long id) {
        projectRepository.delete(id);
    }

}
