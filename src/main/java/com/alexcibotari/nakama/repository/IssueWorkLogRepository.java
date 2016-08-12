package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueWorkLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueWorkLogRepository extends CrudRepository<IssueWorkLog, Long> {

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE iwl.issue.project.id = :projectId AND iwl.issue.idInProject = :idInProject ORDER BY iwl.createdDate")
    List<IssueWorkLog> findAll(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE iwl.issue.project.key = :projectKey AND iwl.issue.idInProject = :idInProject ORDER BY iwl.createdDate")
    List<IssueWorkLog> findAll(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE iwl.issue.id = :issueId ORDER BY iwl.createdDate")
    List<IssueWorkLog> findAll(@Param("issueId") Long issueId);

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE CONCAT(iwl.issue.project.key, '-', iwl.issue.idInProject) = :issueKey ORDER BY iwl.createdDate")
    List<IssueWorkLog> findAll(@Param("issueKey") String issueKey);

    @Query("SELECT IFNULL(MAX(idInIssue),0)+1 FROM IssueWorkLog WHERE issue.id = :issueId")
    Long getNextIdInIssue(@Param("issueId") Long issueId);

    @Query("SELECT IFNULL(MAX(idInIssue),0)+1 FROM IssueWorkLog WHERE CONCAT(issue.project.key, '-', issue.idInProject) = :issueKey")
    Long getNextIdInIssue(@Param("issueKey") String issueKey);

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE iwl.issue.project.key = :projectKey AND iwl.issue.idInProject = :idInProject AND iwl.idInIssue = :idInIssue")
    IssueWorkLog findOne(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE iwl.issue.project.id = :projectId AND iwl.issue.idInProject = :idInProject AND iwl.idInIssue = :idInIssue")
    IssueWorkLog findOne(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("SELECT iwl FROM IssueWorkLog iwl WHERE CONCAT(iwl.issue.project.key, '-', iwl.issue.idInProject) = :issueKey AND iwl.idInIssue = :idInIssue")
    IssueWorkLog findOne(@Param("issueKey") String issueKey, @Param("idInIssue") Long idInIssue);

    @Modifying
    @Query("DELETE FROM IssueWorkLog WHERE issue.project.key = :projectKey AND issue.idInProject = :idInProject AND idInIssue = :idInIssue")
    void delete(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Modifying
    @Query("DELETE FROM IssueWorkLog WHERE issue.project.id = :projectId AND issue.idInProject = :idInProject AND idInIssue = :idInIssue")
    void delete(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Modifying
    @Query("DELETE FROM IssueWorkLog WHERE CONCAT(issue.project.key, '-', issue.idInProject) = :issueKey AND idInIssue = :idInIssue")
    void delete(@Param("issueKey") String issueKey, @Param("idInIssue") Long idInIssue);
}
