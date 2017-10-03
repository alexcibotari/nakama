package com.alexcibotari.nakama.graphql.resolvers;

import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.service.AuthorityService;
import com.alexcibotari.nakama.service.UserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {


    private UserService userService;
    private AuthorityService authorityService;

    @Autowired
    public Query(UserService service, AuthorityService authorityService) {
        this.userService = service;
        this.authorityService = authorityService;
    }

    public Optional<User> me() {
        return userService.getUser();
    }

    public List<User> users() {
        return userService.findAll();
    }

    public Optional<User> user(String login) {
        return userService.findOneByLogin(login);
    }

    public List<Authority> authorities() {
        return authorityService.findAll();
    }

    public Optional<Authority> authority(String name) {
        return authorityService.findOneByName(name);
    }

    public String hello(String user, String pass) {
        System.out.println(user + ":" + pass);
        return user + ":" + pass;
    }
}
