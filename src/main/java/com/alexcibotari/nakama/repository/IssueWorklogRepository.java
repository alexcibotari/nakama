package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueWorklog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueWorklogRepository extends CrudRepository<IssueWorklog, Long> {

    @Query("SELECT iw FROM IssueWorklog iw WHERE iw.issue.project.id = :projectId AND iw.issue.idInProject = :idInProject ORDER BY iw.createdDate")
    List<IssueWorklog> findAll(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Query("SELECT iw FROM IssueWorklog iw WHERE iw.issue.project.key = :projectKey AND iw.issue.idInProject = :idInProject ORDER BY iw.createdDate")
    List<IssueWorklog> findAll(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT iw FROM IssueWorklog iw WHERE iw.issue.id = :issueId ORDER BY iw.createdDate")
    List<IssueWorklog> findAll(@Param("issueId") Long issueId);

    @Query("SELECT iw FROM IssueWorklog iw WHERE CONCAT(iw.issue.project.key, '-', iw.issue.idInProject) = :issueKey ORDER BY iw.createdDate")
    List<IssueWorklog> findAll(@Param("issueKey") String issueKey);

    @Query("SELECT IFNULL(MAX(idInIssue),0)+1 FROM IssueWorklog WHERE issue.id = :issueId")
    Long getNextIdInIssue(@Param("issueId") Long issueId);

    @Query("SELECT IFNULL(MAX(idInIssue),0)+1 FROM IssueWorklog WHERE CONCAT(issue.project.key, '-', issue.idInProject) = :issueKey")
    Long getNextIdInIssue(@Param("issueKey") String issueKey);

    @Query("SELECT iw FROM IssueWorklog iw WHERE iw.issue.project.key = :projectKey AND iw.issue.idInProject = :idInProject AND iw.idInIssue = :idInIssue")
    IssueWorklog findOne(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("SELECT iw FROM IssueWorklog iw WHERE iw.issue.project.id = :projectId AND iw.issue.idInProject = :idInProject AND iw.idInIssue = :idInIssue")
    IssueWorklog findOne(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("SELECT iw FROM IssueWorklog iw WHERE CONCAT(iw.issue.project.key, '-', iw.issue.idInProject) = :issueKey AND iw.idInIssue = :idInIssue")
    IssueWorklog findOne(@Param("issueKey") String issueKey, @Param("idInIssue") Long idInIssue);

    @Query("DELETE FROM IssueWorklog WHERE issue.project.key = :projectKey AND issue.idInProject = :idInProject AND idInIssue = :idInIssue")
    void delete(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("DELETE FROM IssueWorklog WHERE issue.project.id = :projectId AND issue.idInProject = :idInProject AND idInIssue = :idInIssue")
    void delete(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("DELETE FROM IssueWorklog WHERE CONCAT(issue.project.key, '-', issue.idInProject) = :issueKey AND idInIssue = :idInIssue")
    void delete(@Param("issueKey") String issueKey, @Param("idInIssue") Long idInIssue);
}
