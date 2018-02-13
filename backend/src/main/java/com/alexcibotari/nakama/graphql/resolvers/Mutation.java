package com.alexcibotari.nakama.graphql.resolvers;

import com.alexcibotari.nakama.graphql.types.AddUserInput;
import com.alexcibotari.nakama.graphql.types.UserPayload;
import com.alexcibotari.nakama.service.AuthorityService;
import com.alexcibotari.nakama.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {


  private UserService userService;
  private AuthorityService authorityService;

  public Mutation(UserService service, AuthorityService authorityService) {
    this.userService = service;
    this.authorityService = authorityService;
  }

  public UserPayload addUser(AddUserInput input) {
    return null;
  }

}
