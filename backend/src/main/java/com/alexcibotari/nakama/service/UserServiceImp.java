package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.exception.UserEmailAlreadyInUseException;
import com.alexcibotari.nakama.exception.UserLoginAlreadyInUseException;
import com.alexcibotari.nakama.repository.UserRepository;
import com.alexcibotari.nakama.security.SecurityUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

  private UserRepository repository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImp(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  private void checkLoginAndEmail(String login, String email) {
    checkLogin(login);
    checkEmail(email);
  }

  private void checkLogin(String login) {
    findOneByLogin(login.toLowerCase()).ifPresent(user -> {
      throw new UserLoginAlreadyInUseException();
    });
  }

  private void checkEmail(String email) {
    findOneByEmail(email.toLowerCase()).ifPresent(user -> {
      throw new UserEmailAlreadyInUseException();
    });
  }

  public Optional<User> findOneByLogin(String login) {
    return repository.findOneByLogin(login);
  }

  public Optional<User> findOneByEmail(String email) {
    return repository.findOneByEmail(email);
  }

  public Optional<User> getCurrentUser() {
    return findOneByLogin(SecurityUtils.getCurrentUserLogin());
  }

  public List<User> findAll() {
    return (List<User>) repository.findAll();
  }

  @Transactional
  public Optional<User> delete(String name) {
    Optional<User> entity = repository.findOneByLogin(name);
    entity.ifPresent(user -> repository.delete(user));
    return entity;
  }
}
