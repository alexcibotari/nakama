package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.web.resource.AuthorityResource;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    Optional<Authority> findOne(Long id);

    List<Authority> findAll();

    List<Authority> findAllByUserName(String name);

    Authority create(AuthorityResource resource);

    Optional<Authority> update(Long id, AuthorityResource resource);

    Optional<Authority> delete(Long id);

}
