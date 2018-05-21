package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.model.User;
import com.alexcibotari.nakama.repository.UserRepository;
import com.alexcibotari.nakama.security.SecurityUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private UserRepository repository;

  @Autowired
  public UserServiceImp(UserRepository repository) {
    this.repository = repository;
  }

  public Optional<User> findOneByLogin(String login) {
    return repository.findOneByLogin(login);
  }

  public Optional<User> getCurrentUser() {
    return findOneByLogin(SecurityUtils.getCurrentUserLogin());
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  /**
   * Delete user.
   */
  public Optional<User> delete(String name) {
    Optional<User> entity = repository.findOneByLogin(name);
    entity.ifPresent(user -> repository.delete(user));
    return entity;
  }
}
