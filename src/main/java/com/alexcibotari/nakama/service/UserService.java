package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findOneByUserName(String username);

    public Optional<User> findOneByEmail(String email);

    public User createUser(UserDTO user);

    public User getUser();

    public List<User> findAll();
}
