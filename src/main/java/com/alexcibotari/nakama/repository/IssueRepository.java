package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Issue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findAllByProjectId(Long id);

    List<Issue> findAllByProjectKey(String key);

    @Query("SELECT IFNULL(MAX(idInProject),0)+1 FROM Issue WHERE project.id = :projectId")
    Long getNextIdInProject(@Param("projectId") Long projectId);

    @Query("SELECT IFNULL(MAX(idInProject),0)+1 FROM Issue WHERE project.key = :projectKey")
    Long getNextIdInProject(@Param("projectKey") String projectKey);

    @Query("SELECT i FROM Issue i WHERE i.project.key = :projectKey AND i.idInProject = :idInProject")
    Issue findOne(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT i FROM Issue i WHERE i.project.id = :projectId AND i.idInProject = :idInProject")
    Issue findOne(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Query("SELECT i FROM Issue i WHERE CONCAT(i.project.key, '-', i.idInProject) = :issueKey")
    Issue findOne(@Param("issueKey") String issueKey);

    @Modifying
    @Query("DELETE FROM Issue WHERE project.key = :projectKey AND idInProject = :idInProject")
    void delete(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Modifying
    @Query("DELETE FROM Issue WHERE project.id = :projectId AND idInProject = :idInProject")
    void delete(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Modifying
    @Query("DELETE FROM Issue WHERE CONCAT(project.key, '-', idInProject) = :issueKey")
    void delete(@Param("issueKey") String issueKey);
}
