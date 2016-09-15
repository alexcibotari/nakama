package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.web.rest.resource.ProjectResource;

import java.util.List;

public interface ProjectService {

    public Project findOne(Long id);

    public Project findOneByKey(String key);

    public List<Project> findAll();

    public Project create(ProjectResource resource);

    public Project update(Long id, ProjectResource resource);

    public void delete(Long id);
}
