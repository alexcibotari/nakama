package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
