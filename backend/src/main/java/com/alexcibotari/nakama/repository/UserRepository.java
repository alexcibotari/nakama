package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByName(String name);

    Optional<User> findOneByEmail(String email);
}
