package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.WorkLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkLogRepository extends CrudRepository<WorkLog, Long> {

    Optional<WorkLog> findOneById(Long id);

    @Query("SELECT iwl FROM WorkLog iwl WHERE iwl.issue.project.id = :projectId AND iwl.issue.idInProject = :idInProject ORDER BY iwl.createdDate")
    List<WorkLog> findAll(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Query("SELECT iwl FROM WorkLog iwl WHERE iwl.issue.project.key = :projectKey AND iwl.issue.idInProject = :idInProject ORDER BY iwl.createdDate")
    List<WorkLog> findAll(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT iwl FROM WorkLog iwl WHERE iwl.issue.id = :issueId ORDER BY iwl.createdDate")
    List<WorkLog> findAll(@Param("issueId") Long issueId);

    @Query("SELECT iwl FROM WorkLog iwl WHERE CONCAT(iwl.issue.project.key, '-', iwl.issue.idInProject) = :issueKey ORDER BY iwl.createdDate")
    List<WorkLog> findAll(@Param("issueKey") String issueKey);

    @Modifying
    Optional<WorkLog> deleteOneById(Long id);
}
