package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.repository.AuthorityRepository;
import com.alexcibotari.nakama.web.resource.AuthorityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorityServiceImp implements AuthorityService {

    @Autowired
    AuthorityRepository repository;

    public Optional<Authority> findOne(String id) {
        return repository.findOneById(id);
    }
    public Optional<Authority> findOneByName(String name){
        return repository.findOneByName(name);
    }

    public List<Authority> findAll() {
        return (List<Authority>) repository.findAll();
    }
    public List<Authority> findAllByUserLogin(String login) {
        return repository.findAllByUserLogin(login);
    }

    @Transactional
    public Authority create(AuthorityResource resource) {
        Authority entity = new Authority();
        entity.setName(resource.getName());
        return repository.save(entity);
    }

    @Transactional
    public Optional<Authority> update(String id, AuthorityResource resource) {
        return findOne(id)
            .map(entity -> {
                entity.setName(resource.getName());
                return repository.save(entity);
            });
    }

    @Transactional
    public Optional<Authority> delete(String id) {
        Optional<Authority> entity = findOne(id);
        entity.ifPresent(authority -> repository.delete(authority));
        return entity;
    }

}
