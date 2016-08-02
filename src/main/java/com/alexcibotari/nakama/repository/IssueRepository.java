package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findAllByProjectId(Long id);

    List<Issue> findAllByProjectKey(String key);

    @Query("SELECT IFNULL(MAX(idInProject),0)+1 FROM Issue WHERE project.id = :projectId")
    Long getNextIdInProjectByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT IFNULL(MAX(idInProject),0)+1 FROM Issue WHERE project.key = :projectKey")
    Long getNextIdInProjectByProjectKey(@Param("projectKey") String projectKey);

    @Query("SELECT i FROM Issue i WHERE i.project.key = :projectKey AND i.idInProject = :idInProject")
    Issue findOneByKeys(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT i FROM Issue i WHERE i.project.id = :projectId AND i.idInProject = :idInProject")
    Issue findOneByIds(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);
}
