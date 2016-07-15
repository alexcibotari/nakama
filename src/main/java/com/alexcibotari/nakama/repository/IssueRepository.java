package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    public List<Issue> findAllByProjectId(Long id);

    @Query("SELECT count(*) FROM Issue WHERE project.id = :projectId")
    public Long getNextIssueKeyByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT i FROM Issue i WHERE i.project.key = :projectKey AND i.key = :issueKey")
    public Issue findOneByKeys(@Param("projectKey") String projectKey, @Param("issueKey") Long issueKey);
}
