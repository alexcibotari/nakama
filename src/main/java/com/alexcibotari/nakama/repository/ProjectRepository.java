package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
