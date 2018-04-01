package com.alexcibotari.nakama.graphql.resolvers;

import com.alexcibotari.nakama.graphql.types.AddUserInput;
import com.alexcibotari.nakama.graphql.types.UserPayload;
import com.alexcibotari.nakama.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {


  private UserService userService;

  public Mutation(UserService service) {
    this.userService = service;
  }

  public UserPayload addUser(AddUserInput input) {
    return null;
  }

}
