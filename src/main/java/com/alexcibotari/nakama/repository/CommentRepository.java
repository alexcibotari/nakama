package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findOneById(Long id);

    @Query("SELECT ic FROM Comment ic WHERE ic.issue.project.id = :projectId AND ic.issue.idInProject = :idInProject ORDER BY ic.createdDate")
    List<Comment> findAll(@Param("projectId") Long projectId, @Param("idInProject") Long idInProject);

    @Query("SELECT ic FROM Comment ic WHERE ic.issue.project.key = :projectKey AND ic.issue.idInProject = :idInProject ORDER BY ic.createdDate")
    List<Comment> findAll(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);

    @Query("SELECT ic FROM Comment ic WHERE ic.issue.id = :issueId ORDER BY ic.createdDate")
    List<Comment> findAll(@Param("issueId") Long issueId);

    @Query("SELECT ic FROM Comment ic WHERE CONCAT(ic.issue.project.key, '-', ic.issue.idInProject) = :issueKey ORDER BY ic.createdDate")
    List<Comment> findAll(@Param("issueKey") String issueKey);

    @Modifying
    Optional<Comment> deleteOneById(Long id);
}
