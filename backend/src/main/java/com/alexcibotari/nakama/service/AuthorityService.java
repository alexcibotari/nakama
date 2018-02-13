package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.Authority;
import java.util.List;
import java.util.Optional;

public interface AuthorityService {

  Optional<Authority> findOne(String id);

  Optional<Authority> findOneByName(String name);

  List<Authority> findAll();

  List<Authority> findAllByUserLogin(String login);

  Optional<Authority> delete(String id);

}
