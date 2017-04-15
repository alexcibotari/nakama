package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.repository.ProjectRepository;
import com.alexcibotari.nakama.web.resource.ProjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Optional<Project> findOne(Long id) {
        return projectRepository.findOneById(id);
    }

    public Optional<Project> findOne(String key) {
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
    public Optional<Project> update(Long id, ProjectResource resource) {
        return update(findOne(id), resource);
    }

    @Transactional
    public Optional<Project> update(String key, ProjectResource resource) {
        return update(findOne(key), resource);
    }

    private Optional<Project> update(Optional<Project> project, ProjectResource resource) {
        return project
            .map(entity -> {
                entity.setKey(resource.getKey());
                entity.setName(resource.getName());
                entity.setDescription(resource.getDescription());
                return projectRepository.save(entity);
            });
    }

    @Transactional
    public Optional<Project> delete(String key) {
        return projectRepository.deleteOneByKey(key);
    }

}
