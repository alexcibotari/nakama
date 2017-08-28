package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.Personal;
import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.exception.UserEmailAlreadyInUseException;
import com.alexcibotari.nakama.exception.UserLoginAlreadyInUseException;
import com.alexcibotari.nakama.repository.UserRepository;
import com.alexcibotari.nakama.security.SecurityUtils;
import com.alexcibotari.nakama.service.util.RandomUtil;
import com.alexcibotari.nakama.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(UserResource resource) {
        checkLoginAndEmail(resource.getLogin(), resource.getEmail());

        User entity = new User();
        updateEntity(entity, resource);
        entity.setPassword(passwordEncoder.encode(RandomUtil.generatePassword()));

        return repository.save(entity);
    }

    @Transactional
    public Optional<User> update(String login, UserResource resource) {
        return findOneByLogin(login)
            .map(entity -> {
                updateEntity(entity, resource);
                return repository.save(entity);
            });
    }

    private void updateEntity(User entity, UserResource resource) {
        entity.setEmail(resource.getEmail());
        entity.setEnabled(resource.getEnabled());
        if (resource.getPersonal() != null) {
            Personal personal = new Personal();
            personal.setGivenName(resource.getPersonal().getGivenName());
            personal.setFamilyName(resource.getPersonal().getFamilyName());
            personal.setBirthday(resource.getPersonal().getBirthday());
            entity.setPersonal(personal);
        }
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

    public Optional<User> getUser() {
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
