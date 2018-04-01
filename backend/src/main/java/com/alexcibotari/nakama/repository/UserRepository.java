package com.alexcibotari.nakama.repository;

import com.alexcibotari.nakama.model.User;
import java.util.Optional;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;

public interface UserRepository extends MapIdCassandraRepository<User> {

  Optional<User> findOneByLogin(String login);
}
