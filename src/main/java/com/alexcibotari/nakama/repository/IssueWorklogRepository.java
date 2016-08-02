package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueWorklog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueWorklogRepository extends CrudRepository<IssueWorklog, Long> {

    @Query("SELECT iw FROM IssueWorklog iw WHERE iw.issue.project.key = :projectKey AND iw.issue.idInProject = :idInProject ORDER BY iw.createdDate")
    List<IssueWorklog> findAllByIssueKey(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);
}
