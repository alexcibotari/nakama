package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.rest.resource.UserResource;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOneByUserName(String username);

    Optional<User> findOneByEmail(String email);

    User create(UserResource resource);

    Optional<User> update(String userName, UserResource resource);

    Optional<User> getUser();

    List<User> findAll();

    Optional<User> delete(String userName);
}
