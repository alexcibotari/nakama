package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    public Issue findOneByKey(String key);
    public List<Issue> findAllByProjectId(Long id);
}
