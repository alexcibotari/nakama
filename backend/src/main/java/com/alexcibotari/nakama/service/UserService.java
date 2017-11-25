package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.resource.UserResource;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneByEmail(String email);

    User create(UserResource resource);

    Optional<User> update(String login, UserResource resource);

    Optional<User> getCurrentUser();

    List<User> findAll();

    Optional<User> delete(String login);
}
