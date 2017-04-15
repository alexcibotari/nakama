package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IssueStatusRepository extends CrudRepository<IssueStatus, Long> {
    Optional<IssueStatus> findOneById(Long id);

    @Modifying
    Optional<IssueStatus> deleteOneById(Long id);
}
