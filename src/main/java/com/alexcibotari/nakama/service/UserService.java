package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.rest.resource.UserResource;

import java.util.List;

public interface UserService {

    User findOneByUserName(String username);

    User findOneByEmail(String email);

    User create(UserResource resource);

    User update(String userName, UserResource resource);

    User getUser();

    List<User> findAll();

    void delete(String userName);
}
