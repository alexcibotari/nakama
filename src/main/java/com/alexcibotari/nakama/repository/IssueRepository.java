package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    public List<Issue> findAllByProjectId(Long id);

    public List<Issue> findAllByProjectKey(String key);

    @Query("SELECT count(*)+1 FROM Issue WHERE project.id = :projectId")
    public Long getNextInProjectIdByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT count(*)+1 FROM Issue WHERE project.key = :projectKey")
    public Long getNextInProjectIdByProjectKey(@Param("projectKey") String projectId);

    @Query("SELECT i FROM Issue i WHERE i.project.key = :projectKey AND i.idInProject = :idInProject")
    public Issue findOneByKeys(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT i FROM Issue i WHERE i.project.id = :projectId AND i.idInProject = :idInProject")
    public Issue findOneByIds(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);
}
