package com.alexcibotari.nakama.repository;

import com.alexcibotari.nakama.model.Authority;
import java.util.Optional;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AuthorityRepository extends CassandraRepository<Authority> {

  Optional<Authority> findOneById(String id);

}
