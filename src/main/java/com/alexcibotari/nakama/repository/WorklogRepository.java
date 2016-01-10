package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Worklog;
import org.springframework.data.repository.CrudRepository;

public interface WorklogRepository extends CrudRepository<Worklog, Long> {
}
