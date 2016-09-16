package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> findOneBy(Long id);
    Project findOneByKey(String key);
    void deleteOneByKey(String key);
}
