package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;

public interface UserService {


    public User createUser(UserDTO user);


}