package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneByEmail(String email);
}
