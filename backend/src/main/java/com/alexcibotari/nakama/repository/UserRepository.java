package com.alexcibotari.nakama.repository;

import com.alexcibotari.nakama.model.User;
import java.util.Optional;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<User> {

  Optional<User> findOneByLogin(String login);
}
