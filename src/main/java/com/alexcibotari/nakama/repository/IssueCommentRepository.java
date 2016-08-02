package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueCommentRepository extends CrudRepository<IssueComment, Long> {

    @Query("SELECT ic FROM IssueComment ic WHERE ic.issue.project.id = :projectId AND ic.issue.idInProject = :idInProject ORDER BY ic.createdDate")
    List<IssueComment> findAll(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Query("SELECT ic FROM IssueComment ic WHERE ic.issue.project.key = :projectKey AND ic.issue.idInProject = :idInProject ORDER BY ic.createdDate")
    List<IssueComment> findAll(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT ic FROM IssueComment ic WHERE ic.issue.id = :issueId ORDER BY ic.createdDate")
    List<IssueComment> findAll(@Param("issueId") Long issueId);

    @Query("SELECT ic FROM IssueComment ic WHERE CONCAT(ic.issue.project.key, '-', ic.issue.idInProject) = :issueKey ORDER BY ic.createdDate")
    List<IssueComment> findAll(@Param("issueKey") String issueKey);

    @Query("SELECT IFNULL(MAX(idInIssue),0)+1 FROM IssueComment WHERE issue.id = :issueId")
    Long getNextIdInIssue(@Param("issueId") Long issueId);

    @Query("SELECT IFNULL(MAX(idInIssue),0)+1 FROM IssueComment WHERE CONCAT(issue.project.key, '-', issue.idInProject) = :issueKey")
    Long getNextIdInIssue(@Param("issueKey") String issueKey);

    @Query("SELECT ic FROM IssueComment ic WHERE ic.issue.project.key = :projectKey AND ic.issue.idInProject = :idInProject AND ic.idInIssue = :idInIssue ORDER BY ic.createdDate")
    IssueComment findOne(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("SELECT ic FROM IssueComment ic WHERE ic.issue.project.id = :projectId AND ic.issue.idInProject = :idInProject AND ic.idInIssue = :idInIssue ORDER BY ic.createdDate")
    IssueComment findOne(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject, @Param("idInIssue") Long idInIssue);

    @Query("SELECT ic FROM IssueComment ic WHERE CONCAT(ic.issue.project.key, '-', ic.issue.idInProject) = :issueKey AND ic.idInIssue = :idInIssue ORDER BY ic.createdDate")
    IssueComment findOne(@Param("issueKey") String issueKey, @Param("idInIssue") Long idInIssue);
}
