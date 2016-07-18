package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.IssueType;
import org.springframework.data.repository.CrudRepository;

public interface IssueTypeRepository extends CrudRepository<IssueType, Long> {
}
