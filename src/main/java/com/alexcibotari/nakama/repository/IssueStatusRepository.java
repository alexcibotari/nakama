package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueStatus;
import org.springframework.data.repository.CrudRepository;

public interface IssueStatusRepository extends CrudRepository<IssueStatus, Long> {
}
