package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> findOneByLogin(String login);

  Optional<User> findOneByEmail(String email);

  Optional<User> getCurrentUser();

  List<User> findAll();

  Optional<User> delete(String login);
}
