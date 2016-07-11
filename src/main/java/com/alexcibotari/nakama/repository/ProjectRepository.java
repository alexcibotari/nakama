package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    public Project findOneByKey(String key);
    public void deleteOneByKey(String key);
}
