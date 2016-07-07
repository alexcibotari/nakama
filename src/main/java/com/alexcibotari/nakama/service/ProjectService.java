package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.web.rest.dto.ProjectDTO;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public Project findOneById(Long id);

    public Project findOneByKey(String key);

    public List<Project> findAll();

    public Project create(ProjectDTO dto);

    public Project update(ProjectDTO dto);

    public void delete(String key);
}