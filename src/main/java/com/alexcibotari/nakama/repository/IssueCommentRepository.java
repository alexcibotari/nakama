package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueCommentRepository extends CrudRepository<IssueComment, Long> {

    @Query("SELECT ic FROM IssueComment ic WHERE ic.issue.project.key = :projectKey AND ic.issue.idInProject = :idInProject ORDER BY ic.createdDate")
    List<IssueComment> findAllByIssueKey(@Param("projectKey") String projectKey, @Param("idInProject") Long idInProject);
}
