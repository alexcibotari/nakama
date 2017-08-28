package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.web.resource.AuthorityResource;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    Optional<Authority> findOne(String id);
    Optional<Authority> findOneByName(String name);

    List<Authority> findAll();

    List<Authority> findAllByUserLogin(String login);

    Authority create(AuthorityResource resource);

    Optional<Authority> update(String id, AuthorityResource resource);

    Optional<Authority> delete(String id);

}
