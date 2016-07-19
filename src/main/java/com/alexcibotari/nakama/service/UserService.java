package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findOneByUserName(String username);

    User findOneByEmail(String email);

    User create(UserDTO user);

    User update(UserDTO userDTO);

    User getUser();

    List<User> findAll();

    void delete(String userName);
}
