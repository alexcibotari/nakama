package com.alexcibotari.nakama.repository;

import com.alexcibotari.nakama.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, String> {

    Optional<Authority> findOneById(String id);

    Optional<Authority> findOneByName(String name);

    @Query("SELECT u.authorities FROM User u WHERE u.login = :login")
    List<Authority> findAllByUserLogin(@Param("login") String login);
}
