package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.repository.AuthorityRepository;
import com.alexcibotari.nakama.repository.UserRepository;
import com.alexcibotari.nakama.service.util.RandomUtil;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(RandomUtil.generatePassword()));

        Set<Authority> authorities = new HashSet<>();
        userDTO.getAuthorities().stream().forEach(authority -> authorities.add(authorityRepository.findOne(authority)));
        user.setAuthorities(authorities);

        return userRepository.save(user);
    }
}