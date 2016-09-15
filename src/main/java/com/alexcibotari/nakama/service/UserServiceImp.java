package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;
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
        User user = new User();
        user.setUserName(resource.getUserName());
        user.setEmail(resource.getEmail());
        user.setEnabled(resource.getEnabled());
        user.setPassword(passwordEncoder.encode(RandomUtil.generatePassword()));

        Set<Authority> authorities = new HashSet<>();
        resource.getAuthorities().stream().forEach(authority -> authorities.add(authorityRepository.findOneByName(authority)));
        user.setAuthorities(authorities);

        return userRepository.save(user);
    }

    @Transactional
    public User update(String userName, UserResource resource) {
        User user = userRepository.findOneByUserName(userName).get();
        user.setEmail(resource.getEmail());
        user.setEnabled(resource.getEnabled());

        Set<Authority> authorities = new HashSet<>();
        resource.getAuthorities().stream().forEach(authority -> authorities.add(authorityRepository.findOneByName(authority)));
        user.setAuthorities(authorities);

        return userRepository.save(user);
    }

    public User findOneByUserName(String username) {
        return userRepository.findOneByUserName(username).get();
    }

    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email).get();
    }

    public User getUser() {
        return userRepository.findOneByUserName(SecurityUtils.getCurrentUserName()).get();
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public void delete(String userName) {
        userRepository.deleteByUserName(userName);
    }
}
