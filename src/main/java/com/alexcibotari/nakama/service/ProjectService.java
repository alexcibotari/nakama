package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.web.rest.resource.ProjectResource;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project findOne(Long id);

    Project findOneByKey(String key);

    List<Project> findAll();

    Project create(ProjectResource resource);

    Project update(Long id, ProjectResource resource);

    Project update(String key, ProjectResource resource);

    void delete(Long id);

    void delete(String key);
}
