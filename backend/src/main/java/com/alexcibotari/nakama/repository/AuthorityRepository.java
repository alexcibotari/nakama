package com.alexcibotari.nakama.repository;

import com.alexcibotari.nakama.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Optional<Authority> findOneById(Long id);

    Optional<Authority> findOneByName(String name);

    @Query("SELECT u.authorities FROM User u WHERE u.name = :name")
    List<Authority> findAllByUserName(@Param("name") String name);
}
