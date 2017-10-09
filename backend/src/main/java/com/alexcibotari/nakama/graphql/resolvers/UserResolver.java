package com.alexcibotari.nakama.graphql.resolvers;

import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

public class UserResolver implements GraphQLResolver<User> {

    public Set<Authority> getAuthorities(User user){
        user.getAuthorities().size();
        return user.getAuthorities();
    }
}
