package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.repository.AuthorityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorityServiceImp implements AuthorityService {

  @Autowired
  AuthorityRepository repository;

  public Optional<Authority> findOne(String id) {
    return repository.findOneById(id);
  }

  public Optional<Authority> findOneByName(String name) {
    return repository.findOneByName(name);
  }

  public List<Authority> findAll() {
    return (List<Authority>) repository.findAll();
  }

  public List<Authority> findAllByUserLogin(String login) {
    return repository.findAllByUserLogin(login);
  }

  public Optional<Authority> delete(String id) {
    Optional<Authority> entity = findOne(id);
    entity.ifPresent(authority -> repository.delete(authority));
    return entity;
  }

}
