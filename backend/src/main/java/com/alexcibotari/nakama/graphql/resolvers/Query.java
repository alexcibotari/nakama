package com.alexcibotari.nakama.graphql.resolvers;

import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.repository.UserRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserRepository repository;

    public List<User> users() {
        return (List<User>) repository.findAll();
    }

    public Optional<User> user(String login){
        return repository.findOneByLogin(login);
    }

    public String hello(String user, String pass){
        System.out.println(user+":"+pass);
        return user+":"+pass;
    }
}
