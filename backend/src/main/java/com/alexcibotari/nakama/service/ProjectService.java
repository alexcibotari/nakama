package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.web.resource.ProjectResource;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Optional<Project> findOne(Long id);

    Optional<Project> findOne(String key);

    List<Project> findAll();

    Project create(ProjectResource resource);

    Optional<Project> update(Long id, ProjectResource resource);

    Optional<Project> update(String key, ProjectResource resource);

    Optional<Project> delete(String key);
}
