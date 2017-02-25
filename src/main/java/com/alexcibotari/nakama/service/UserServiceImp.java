package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.exception.UserEmailAlreadyInUseException;
import com.alexcibotari.nakama.exception.UserLoginAlreadyInUseException;
import com.alexcibotari.nakama.repository.AuthorityRepository;
import com.alexcibotari.nakama.repository.UserRepository;
import com.alexcibotari.nakama.security.SecurityUtils;
import com.alexcibotari.nakama.service.util.RandomUtil;
import com.alexcibotari.nakama.web.rest.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public User create(UserResource resource) {
        checkLoginAndEmail(resource.getLogin(), resource.getEmail());

        User user = new User();
        user.setLogin(resource.getLogin());
        user.setEmail(resource.getEmail());
        user.setEnabled(resource.getEnabled());
        user.setPassword(passwordEncoder.encode(RandomUtil.generatePassword()));

        Set<Authority> authorities = new HashSet<>();
        resource.getAuthorities().forEach(authority -> authorityRepository.findOneByName(authority).map(authorities::add));
        user.setAuthorities(authorities);

        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> update(String userName, UserResource resource) {
        return findOneByLogin(userName)
            .map(entity -> {
                entity.setEmail(resource.getEmail());
                entity.setEnabled(resource.getEnabled());
                Set<Authority> authorities = new HashSet<>();
                resource.getAuthorities().forEach(authority -> authorityRepository.findOneByName(authority).map(authorities::add));
                entity.setAuthorities(authorities);
                return userRepository.save(entity);
            });
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
        return userRepository.findOneByLogin(login);
    }

    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public Optional<User> getUser() {
        return findOneByLogin(SecurityUtils.getCurrentUserName());
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public Optional<User> delete(String login) {
        return userRepository.deleteByLogin(login);
    }
}
