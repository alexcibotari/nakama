package com.alexcibotari.nakama.graphql.resolvers;

import com.alexcibotari.nakama.model.User;
import com.alexcibotari.nakama.service.UserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {


  private UserService userService;

  @Autowired
  public Query(UserService service) {
    this.userService = service;
  }

  public Optional<User> me() {
    return userService.getCurrentUser();
  }

  public List<User> getUsers() {
    return userService.findAll();
  }

  public Optional<User> getUser(String login) {
    return userService.findOneByLogin(login);
  }

  public String hello(String user, String pass) {
    System.out.println(user + ":" + pass);
    return user + ":" + pass;
  }
}
