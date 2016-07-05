package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public Optional<Project> findOneById(Long id);

    public List<Project> findAll();
}