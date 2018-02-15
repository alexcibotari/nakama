package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.model.Authority;
import java.util.List;
import java.util.Optional;

public interface AuthorityService {

  Optional<Authority> findOne(String id);

  List<Authority> findAll();

  Optional<Authority> delete(String id);

}
