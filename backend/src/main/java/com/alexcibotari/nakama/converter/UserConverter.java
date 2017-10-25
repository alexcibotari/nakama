package com.alexcibotari.nakama.converter;

import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.graphql.types.UserPayload;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserPayload> {

    @Override
    public User toEntry(UserPayload resource) {
        return null;
    }

    @Override
    public UserPayload toResource(User entity) {
        return null;
    }

    @Override
    public User updateEntity(User entity, UserPayload resource) {
        return null;
    }
}
